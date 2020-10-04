package io.github.zhyshko.application.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>{

	public Optional<User> findByUsername(String username);
	
}
