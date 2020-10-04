package io.github.zhyshko.application.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.zhyshko.application.dto.UserToken;
import io.github.zhyshko.application.dto.request.LoginRequest;
import io.github.zhyshko.application.dto.request.RefreshTokenRequest;
import io.github.zhyshko.application.dto.request.RegisterRequest;
import io.github.zhyshko.application.dto.response.AuthenticationResponse;
import io.github.zhyshko.application.entity.Role;
import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.entity.VerificationToken;
import io.github.zhyshko.application.repository.UserRepository;
import io.github.zhyshko.application.repository.VerificationTokenRepository;
import io.github.zhyshko.application.security.JwtProvider;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final RefreshTokenService refreshTokenService;

	@Transactional
	public void verifyAccount(String token) {
		String username = verificationTokenRepository.findByToken(token)
				.orElseThrow(() -> new NullPointerException("Token optional has no value")).getUser().getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new NullPointerException("User optional has no value"));
		userRepository.save(user);
	}

	@Transactional
	public UserToken signup(RegisterRequest registerRequest) {
		User user = User.builder().username(registerRequest.getUsername())
				.password(passwordEncoder.encode(registerRequest.getPassword())).role(Role.ADMIN).rating(5.0f).build();
		userRepository.save(user);
		String token = generateVerificationTokenAndSaveItToDatabase(user);
		return UserToken.builder()
				.token(token)
				.build();

	}

	private String generateVerificationTokenAndSaveItToDatabase(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(loginRequest.getUsername())
				.build();
	}

	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenRequest.getRefreshToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(refreshTokenRequest.getUsername())
				.build();
	}

//	public boolean isLoggedIn() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
//	}

}
