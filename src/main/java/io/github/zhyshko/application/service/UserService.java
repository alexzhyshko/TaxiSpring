package io.github.zhyshko.application.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(()->new NullPointerException("No user found by username"));
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(UUID userId) {
		return userRepository.findById(userId).orElseThrow(()->new NullPointerException("No user found by id"));
	}
		
		
	
}
