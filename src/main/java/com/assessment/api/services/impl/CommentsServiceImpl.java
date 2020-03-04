package com.assessment.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.api.dto.CommentsDTO;
import com.assessment.api.entity.Comments;
import com.assessment.api.entity.Posts;
import com.assessment.api.mapper.CommentsMapper;
import com.assessment.api.repository.CommentsRepository;
import com.assessment.api.repository.PostsRepository;
import com.assessment.api.services.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private PostsRepository postRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private CommentsMapper commentsMapper;

	@Override
	public List<CommentsDTO> getAllComments() {
		List<Comments> comments = commentsRepository.findAll();
		if (!comments.isEmpty()) {
			return mapEntityListToDtoList(comments);
		}
		return null;
	}

	private List<CommentsDTO> mapEntityListToDtoList(List<Comments> comments) {
		List<CommentsDTO> commentsDTO = new ArrayList<CommentsDTO>();
		comments.stream().map(comment -> commentsMapper.toDto(comment)).forEach(comment -> commentsDTO.add(comment));
		return commentsDTO;
	}

	@Override
	public List<CommentsDTO> getCommentsByPostId(Integer postId) {
		if (null != postId) {
			Optional<Posts> post = postRepository.findById(postId);
			if (post.isPresent()) {
				Optional<List<Comments>> comments = commentsRepository.findByPost(post.get());
				if (comments.isPresent()) {
					return mapEntityListToDtoList(comments.get());
				}
			}
		}
		return null;
	}

	@Override
	public CommentsDTO getCommentById(Integer id) {
		if (null == id) {
			return null;
		}
		Optional<Comments> comments = commentsRepository.findById(id);
		return comments.isPresent() ? commentsMapper.toDto(comments.get()) : null;
	}

	@Override
	public List<CommentsDTO> getCommentByName(String name) {
		if (null == name) {
			return null;
		}
		Optional<List<Comments>> comments = commentsRepository.findByName(name);
		if (comments.isPresent()) {
			return mapEntityListToDtoList(comments.get());
		}
		return null;
	}

	@Override
	public List<CommentsDTO> getCommentByEmail(String email) {
		if (null == email) {
			return null;
		}
		Optional<List<Comments>> comments = commentsRepository.findByEmail(email);
		if (comments.isPresent()) {
			return mapEntityListToDtoList(comments.get());
		}
		return null;
	}

	@Override
	public List<CommentsDTO> getCommentByBody(String body) {
		if (null == body) {
			return null;
		}
		Optional<List<Comments>> comments = commentsRepository.findByBody(body);
		if (comments.isPresent()) {
			return mapEntityListToDtoList(comments.get());
		}
		return null;
	}

	@Override
	public void addComment(Integer postId, String name, String body, String email) {
		Comments comment = new Comments();
		saveComment(postId, name, body, email, comment);
	}

	private void saveComment(Integer postId, String name, String body, String email, Comments comment) {
		comment.setBody(body);
		comment.setEmail(email);
		comment.setName(name);
		if (null != postId) {
			Optional<Posts> post = postRepository.findById(postId);
			if (post.isPresent()) {
				comment.setPost(post.get());
			}
		}
		commentsRepository.save(comment);
	}

	@Override
	public void updateComment(Integer id, Integer postId, String name, String body, String email) {
		if (null != id) {
			Optional<Comments> comment = commentsRepository.findById(id);
			if (comment.isPresent()) {
				saveComment(postId, name, body, email, comment.get());
			}
		}
	}

	@Override
	public void deleteComment(Integer id) {
		if (null != id) {
			Optional<Comments> comment = commentsRepository.findById(id);
			if (comment.isPresent()) {
				commentsRepository.delete(comment.get());
			}
		}
	}

}
