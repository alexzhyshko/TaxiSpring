package io.github.zhyshko.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.dto.request.LoginRequest;
import io.github.zhyshko.application.dto.request.RefreshTokenRequest;
import io.github.zhyshko.application.dto.request.RegisterRequest;
import io.github.zhyshko.application.dto.response.LoginResponse;
import io.github.zhyshko.application.dto.response.RefreshTokenResponse;
import io.github.zhyshko.application.exception.DuplicateLoginException;
import io.github.zhyshko.application.service.AuthService;
import io.github.zhyshko.application.service.RefreshTokenService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;

	@PostMapping("/register")
	public ResponseEntity<String> signup(@RequestBody(required=true) RegisterRequest registerRequest) {
		try {
			authService.signup(registerRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("User with this e-mail or username exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody(required=true) LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

	@GetMapping("/refreshToken")
	public RefreshTokenResponse refreshTokens(@RequestParam(required=true) String refreshToken, @RequestParam(required=true) String token, @RequestParam(required=true) String username) {
		return authService.refreshToken(refreshToken, username);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody(required=true) RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
	}
	
	@ExceptionHandler({ DuplicateLoginException.class})
    public ResponseEntity<String> handleDuplicateLoginException(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler({ Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
