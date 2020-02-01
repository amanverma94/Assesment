package com.assessment.api.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.api.dto.PostsDTO;
import com.assessment.api.entity.Posts;
import com.assessment.api.mapper.PostsMapper;
import com.assessment.api.repository.PostsRepository;
import com.assessment.api.services.PostsService;

@Service
public class PostsServiceImpl implements PostsService {

	@Value("${external.api.url}")
	private String externalAPI;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private PostsRepository postRepository;
	
	@Autowired
	private PostsMapper postsMapper;

	@Override
	public void saveAllPostsToDB() {
		ResponseEntity<PostsDTO[]>  responseEntity = restTemplate.getForEntity(externalAPI + "/posts", PostsDTO[].class);
		 List<PostsDTO> objects = Arrays.asList(responseEntity.getBody());
		for(PostsDTO obj : objects) {
			Posts posts = new Posts();
			posts.setPostId(obj.getId());
			posts.setPostTitle(obj.getTitle());
			posts.setPostBody(obj.getBody());
			posts.setUserId(obj.getUserId());
			postRepository.save(posts);
		}
	}

	@Override
	public List<PostsDTO> getAllPosts() {
		List<Posts> posts = postRepository.findAll();
		if(!posts.isEmpty()) {
			return mapEntityListToDtoList(posts);
		}
		return null;
	}

	private List<PostsDTO> mapEntityListToDtoList(List<Posts> posts) {
		List<PostsDTO> postDTO = new ArrayList<PostsDTO>();
		for(Posts post : posts) {
			postDTO.add(postsMapper.toDto(post));
		}
		 return postDTO;
	}

	@Override
	public List<PostsDTO> getAllPostsByUserId(Integer userId) {
		Optional<List<Posts>> posts = postRepository.findByUserId(userId);
		if(posts.isPresent()) {
			return mapEntityListToDtoList(posts.get());
		}
		return null;
	}

	@Override
	public PostsDTO getPostById(Integer postId) {
		Optional<Posts> post = postRepository.findByPostId(postId);
		return postsMapper.toDto(post.get());
	}

	@Override
	public List<PostsDTO> getPostByTitleContent(String title) {
		Optional<List<Posts>> posts = postRepository.findPostByTitleContent(title);
		if(posts.isPresent()) {
			return mapEntityListToDtoList(posts.get());
		}
		return null;
	}

	@Override
	public List<PostsDTO> getPostByBodyContent(String title) {
		Optional<List<Posts>> posts = postRepository.findPostByBodyContent(title);
		if(posts.isPresent()) {
			return mapEntityListToDtoList(posts.get());
		}
		return null;
	}
}
