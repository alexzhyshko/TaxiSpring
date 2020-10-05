package io.github.zhyshko.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
	private String username;
	private String refreshToken;
	private String token;
	
}
