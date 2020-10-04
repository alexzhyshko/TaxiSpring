package io.github.zhyshko.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.github.zhyshko.application.dto.UserDetailsImpl;
import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username){
		Optional<User> userOptional = userRepository.findByUsername(username);
		User user = userOptional.orElseThrow(()->new NullPointerException("User not found by username"));
		return new UserDetailsImpl(user);
	}
	
}
