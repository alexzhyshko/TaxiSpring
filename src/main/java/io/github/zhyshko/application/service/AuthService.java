package io.github.zhyshko.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.zhyshko.application.dto.misc.UserDetailsImpl;
import io.github.zhyshko.application.dto.misc.UserToken;
import io.github.zhyshko.application.dto.request.LoginRequest;
import io.github.zhyshko.application.dto.request.LogoutRequest;
import io.github.zhyshko.application.dto.request.RegisterRequest;
import io.github.zhyshko.application.dto.response.LoginResponse;
import io.github.zhyshko.application.dto.response.RefreshTokenResponse;
import io.github.zhyshko.application.entity.Role;
import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.entity.VerificationToken;
import io.github.zhyshko.application.exception.DuplicateLoginException;
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

	public LoginResponse login(LoginRequest loginRequest) {
		LoginRegister.addToRegisterIfNotLoggedIn(loginRequest.getUsername()).orElseThrow(()-> new DuplicateLoginException("User is already logged in"));
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		return LoginResponse.builder()
				.token(token)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.username(loginRequest.getUsername())
				.build();
	}
	

	public RefreshTokenResponse refreshToken(String refreshToken, String username) {
		refreshTokenService.validateRefreshToken(refreshToken);
		LoginRegister.addToRegisterIfNotLoggedIn(username);
		String token = jwtProvider.generateTokenWithUsername(username);
		return RefreshTokenResponse.builder()
				.token(token)
				.refreshToken(refreshToken)
				.build();
	}
	
	public Optional<String> getCurrentAuthenticatedUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return Optional.of(((UserDetailsImpl)authentication.getPrincipal()).getUsername());
		}
		return Optional.empty();
	}
	
	public void logout(LogoutRequest logoutRequest) {
		SecurityContextHolder.getContext().setAuthentication(null);
		refreshTokenService.deleteRefreshToken(logoutRequest.getRefreshToken());
	}

}
