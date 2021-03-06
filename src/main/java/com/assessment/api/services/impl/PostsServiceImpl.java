package com.assessment.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.api.dto.PostsDTO;
import com.assessment.api.entity.Comments;
import com.assessment.api.entity.Posts;
import com.assessment.api.entity.UserDetails;
import com.assessment.api.mapper.PostsMapper;
import com.assessment.api.repository.CommentsRepository;
import com.assessment.api.repository.PostsRepository;
import com.assessment.api.services.PostsService;
import com.assessment.api.services.UserDetailService;

@Service
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostsRepository postRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private PostsMapper postsMapper;

	@Autowired
	private UserDetailService userDetailService;

	@Override
	public List<PostsDTO> getAllPosts() {
		List<Posts> posts = postRepository.findAll();
		if (!posts.isEmpty()) {
			return mapEntityListToDtoList(posts);
		}
		return null;
	}

	private List<PostsDTO> mapEntityListToDtoList(List<Posts> posts) {
		List<PostsDTO> postDTO = new ArrayList<PostsDTO>();
		posts.stream().map(post -> postsMapper.toDto(post)).forEach(post -> postDTO.add(post));
		return postDTO;
	}

	@Override
	public List<PostsDTO> getAllPostsByUserId(Integer userId) {
		if (null == userId) {
			return null;
		}
		UserDetails userDetails = userDetailService.getUserDetailsByUserId(userId);
		Optional<List<Posts>> posts = postRepository.findByUser(userDetails);
		if (posts.isPresent()) {
			return mapEntityListToDtoList(posts.get());
		}
		return null;
	}

	@Override
	public PostsDTO getPostById(Integer id) {
		if (null == id) {
			return null;
		}
		Optional<Posts> post = postRepository.findById(id);
		if (post.isPresent()) {
			return postsMapper.toDto(post.get());
		}
		return null;
	}

	@Override
	public List<PostsDTO> getPostByTitleContent(String title) {
		if (null == title) {
			return null;
		}
		Optional<List<Posts>> posts = postRepository.findPostByTitleContent(title);
		if (posts.isPresent()) {
			return mapEntityListToDtoList(posts.get());
		}
		return null;
	}

	@Override
	public List<PostsDTO> getPostByBodyContent(String body) {
		if (null == body) {
			return null;
		}
		Optional<List<Posts>> posts = postRepository.findPostByBodyContent(body);
		if (posts.isPresent()) {
			return mapEntityListToDtoList(posts.get());
		}
		return null;
	}

	@Override
	public void addPost(Integer userId, String title, String body) {
		Posts post = new Posts();
		savePost(userId, title, body, post);
	}

	private void savePost(Integer userId, String title, String body, Posts post) {
		UserDetails userDetails = userDetailService.getUserDetailsByUserId(userId);
		post.setUser(userDetails);
		post.setTitle(title);
		post.setBody(body);
		postRepository.save(post);
	}

	@Override
	public void updatePost(Integer id, Integer userId, String title, String body) {
		if (null != id) {
			Optional<Posts> post = postRepository.findById(id);
			if (post.isPresent()) {
				savePost(userId, title, body, post.get());
			}
		}
	}

	@Override
	public void deletePost(Integer id) {
		if (null != id) {
			Optional<Posts> post = postRepository.findById(id);
			if (post.isPresent()) {
				Optional<List<Comments>> comment = commentsRepository.findByPost(post.get());
				if (comment.isPresent()) {
					commentsRepository.deleteAll(comment.get());
				}
				postRepository.delete(post.get());
			}
		}
	}
}
