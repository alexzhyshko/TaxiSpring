package io.github.zhyshko.application.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.User;
import io.github.zhyshko.application.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, UUID>{

	Optional<VerificationToken> findByToken(String token);
	
	void deleteTokenByUser(User user);
}

