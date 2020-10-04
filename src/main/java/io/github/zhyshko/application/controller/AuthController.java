package io.github.zhyshko.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.dto.AuthenticationResponse;
import io.github.zhyshko.application.dto.LoginRequest;
import io.github.zhyshko.application.dto.RefreshTokenRequest;
import io.github.zhyshko.application.dto.RegisterRequest;
import io.github.zhyshko.application.service.AuthService;
import io.github.zhyshko.application.service.RefreshTokenService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;

	@PostMapping("/register")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		try {
			authService.signup(registerRequest);
		} catch (Exception e) {
			return new ResponseEntity<>("User with this e-mail or username exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

	@PostMapping("/refreshToken")
	public AuthenticationResponse refreshTokens(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return authService.refreshToken(refreshTokenRequest);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
	}
	
	@ExceptionHandler({ Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
