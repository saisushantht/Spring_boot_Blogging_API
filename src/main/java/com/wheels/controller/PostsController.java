package com.wheels.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheels.dto.PostsDto;
import com.wheels.model.Posts;
import com.wheels.service.PostsService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PostsController {
	
	@Autowired
	PostsService postsService;
	
	@PostMapping("/create")
	public ResponseEntity createPost(@RequestBody PostsDto postsDto ) {
		
		postsService.createPost(postsDto);
		return new  ResponseEntity (HttpStatus.OK);
	}
	
	@GetMapping("/all")
    public ResponseEntity<List<PostsDto>> showAllPosts() {
        return new ResponseEntity<>(postsService.findAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostsDto> getSinglePost(@PathVariable @RequestBody String id) {
        return new ResponseEntity<>(postsService.readSinglePost(id), HttpStatus.OK);
    }
    
    @GetMapping("/getByUser/{username}")
    public ResponseEntity<List<PostsDto>> getByUsername(@PathVariable @RequestBody String username) {
        return new ResponseEntity<>(postsService.getByUsername(username), HttpStatus.OK);
    }
    
    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<List<PostsDto>> getByTitle(@PathVariable @RequestBody String title) {
        return new ResponseEntity<>(postsService.getByTitle(title), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable String id, @RequestBody PostsDto postsDto) {
    	
    	getSinglePost(id);
    	postsService.updatePost(id, postsDto);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable String id) {
  
    	
    	postsService.deletePost(id);
    	return new ResponseEntity(HttpStatus.OK);
    }

}
