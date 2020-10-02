package io.github.zhyshko.application.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username){
		Optional<User> userOptional = userRepository.findByUsername(username);
		User user = userOptional.orElseThrow(()->new NullPointerException("User not found by username"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorities(user.getRole().toString().toUpperCase()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role){
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

}
