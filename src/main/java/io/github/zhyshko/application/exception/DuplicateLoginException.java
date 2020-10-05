package io.github.zhyshko.application.exception;

public class DuplicateLoginException extends RuntimeException {

	public DuplicateLoginException(String message) {
		super(message);
	}
	
}
