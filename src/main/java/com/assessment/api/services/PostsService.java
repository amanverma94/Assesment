package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.PostsDTO;

public interface PostsService {

	public List<PostsDTO> getAllPosts();

	public List<PostsDTO> getAllPostsByUserId(Integer userId);

	public PostsDTO getPostById(Integer id);

	public List<PostsDTO> getPostByTitleContent(String title);

	public List<PostsDTO> getPostByBodyContent(String body);

	public void addPost(Integer userId, String title, String body);

	public void updatePost(Integer id, Integer userId, String title, String body);
	
	public void deletePost(Integer id);

}
