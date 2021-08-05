package com.wheels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wheels.dto.PostsDto;
import com.wheels.model.Posts;
import com.wheels.model.User;


@Repository
public interface PostsRepository extends MongoRepository<Posts,Long>  {
	
	Posts findById(String id);
	
	Posts deleteById(String id);
	
	List<Posts> findByUsername(String username);
	
	List<Posts> findByTitle(String title);
	
}
