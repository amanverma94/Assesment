package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.PostsDTO;

public interface PostsService {

	public void saveAllPostsToDB();
	
	public List<PostsDTO> getAllPosts();
	
	public List<PostsDTO> getAllPostsByUserId(Integer userId);
	
	public PostsDTO getPostById(Integer postId);
	
	public List<PostsDTO> getPostByTitleContent(String title);
	
	public List<PostsDTO> getPostByBodyContent(String body);
}
