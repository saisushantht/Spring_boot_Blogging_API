package com.wheels.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wheels.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findById(String id);


	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<User> findByUsername(String username);
}
