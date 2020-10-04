package io.github.zhyshko.application.dto.misc;

import java.time.LocalDateTime;

import lombok.Setter;


@Setter
public class ErrorMessage {

	private LocalDateTime datetime;
	private String message;
	
	
	public ErrorMessage(LocalDateTime datetime, String message) {
		this.datetime = datetime;
		this.message = message;
	}
	
	public String getDatetime() {
		return this.datetime.toString();
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
