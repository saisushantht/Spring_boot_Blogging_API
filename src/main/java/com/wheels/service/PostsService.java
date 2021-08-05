package com.wheels.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wheels.controller.AuthController;
import com.wheels.dto.PostsDto;
import com.wheels.model.Posts;
import com.wheels.repository.PostsRepository;
import static java.util.stream.Collectors.toList;

import java.time.Instant;

@Service
public class PostsService {
	
	
	@Autowired
	PostsRepository postsRepository;
	
	@Autowired
	AuthController authController;
	
	
	@Transactional
	public List<PostsDto> findAllPosts() {
		
		List<Posts> posts = postsRepository.findAll();
		return posts.stream().map(this::mapFromPostsToDto).collect(toList());
	}
	
	 
    public void createPost(PostsDto postDto) {
        Posts posts = mapFromDtoToPosts(postDto);
        postsRepository.save(posts);
    }
	
    
    public PostsDto readSinglePost(String id) {
    	
        Posts posts = postsRepository.findById(id);//.orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostsToDto(posts);
        
    }
    
    
    public List<PostsDto> getByUsername(String username) {
    	
    	List<Posts> posts = postsRepository.findByUsername(username);//.orElseThrow(() -> new PostNotFoundException("For id " + id));
        return posts.stream().map(this::mapFromPostsToDto).collect(toList());
        
    }
    
    public List<PostsDto> getByTitle(String title) {
    	
        List<Posts> posts = postsRepository.findByTitle(title);//.orElseThrow(() -> new PostNotFoundException("For id " + id));
        return posts.stream().map(this::mapFromPostsToDto).collect(toList());
        
    }
    
    
    public Posts updatePost(String id, PostsDto postsDto) {
    	
    	Posts posts = postsRepository.findById(id);
    	UserDetailsImpl myUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	posts.setTitle(postsDto.getTitle());
    	posts.setSubtitle(postsDto.getSubtitle());
    	posts.setDescription(postsDto.getDescription());
    	posts.setImg(postsDto.getImg());
    	posts.setUpdatedOn(Instant.now());
    	posts.setUsername(myUserDetails.getUsername());
    	
    	postsRepository.save(posts);
    	
    	return posts;
    }
   
   public void deletePost(String id) {
	   postsRepository.deleteById(id);
   }
    
	private PostsDto mapFromPostsToDto(Posts posts) {
        PostsDto postsDto = new PostsDto();
        postsDto.setId(posts.getId());
        postsDto.setUsername(posts.getUsername());
        postsDto.setTitle(posts.getTitle());
        postsDto.setSubtitle(posts.getSubtitle());
        postsDto.setImg(posts.getImg());
        postsDto.setDescription(posts.getDescription());
        postsDto.setLikes(posts.getLikes());
        postsDto.setDislikes(posts.getDislikes());
        postsDto.setCreatedOn(posts.getCreatedOn());
        postsDto.setUpdatedOn(posts.getCreatedOn());
        
        return postsDto;
    }
	
	
	private Posts mapFromDtoToPosts(PostsDto postsDto) {
		
		UserDetailsImpl myUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Posts posts = new Posts();
        posts.setTitle(postsDto.getTitle());
        posts.setSubtitle(postsDto.getSubtitle());
        posts.setImg(postsDto.getImg());
        posts.setDescription(postsDto.getDescription());
        //User loggedInUser = authController.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        posts.setCreatedOn(Instant.now());
        posts.setUsername(myUserDetails.getUsername());
        posts.setUpdatedOn(Instant.now());
        
        
        return posts;
    }	
	
}
