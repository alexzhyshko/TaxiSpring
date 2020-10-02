package io.github.zhyshko.application.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
	
}
