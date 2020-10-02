package io.github.zhyshko.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.entity.VerificationToken;
import io.github.zhyshko.application.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenService {

	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@Transactional
	public void deleteTokenByUser(User user) {
		tokenRepository.deleteTokenByUser(user);
	}
	
	public VerificationToken getByToken(String token) {
		return tokenRepository.findByToken(token).orElseThrow(()->new NullPointerException("No token for user"));
	}
	
	

}
