package io.github.zhyshko.application.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/user/getById")
	public ResponseEntity<User> getUserById(@RequestParam Optional<UUID> userId) {
		
	}
	
	@GetMapping("/user/getByUsername")
	public ResponseEntity<User> getUserByUsername(@RequestParam Optional<String> username) {
		
	}
	
}
