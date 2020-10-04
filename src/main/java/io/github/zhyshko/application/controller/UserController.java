package io.github.zhyshko.application.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.converter.entity2dto.UserEntityToDTOConverter;
import io.github.zhyshko.application.dto.User;
import io.github.zhyshko.application.exception.SomethingWentWrongException;
import io.github.zhyshko.application.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/user/getById")
	public ResponseEntity<User> getUserById(@RequestHeader(required=true) String userLocale, @RequestParam(required=true) UUID userId) {
		User user = UserEntityToDTOConverter.convertToDto(this.userService.getUserById(userId));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/getByUsername")
	public ResponseEntity<User> getUserByUsername(@RequestHeader(required=true) String userLocale, @RequestParam(required=true) String username) {
		User user = UserEntityToDTOConverter.convertToDto(this.userService.findUserByUsername(username));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@ExceptionHandler({ SomethingWentWrongException.class})
    public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	
}
