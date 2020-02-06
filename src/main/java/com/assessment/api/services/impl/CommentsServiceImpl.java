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
		for (Comments comment : comments) {
			commentsDTO.add(commentsMapper.toDto(comment));
		}
		return commentsDTO;
	}

	@Override
	public List<CommentsDTO> getCommentsByPostId(Integer postId) {
		Posts post = postRepository.findById(postId).get();
		Optional<List<Comments>> comments = commentsRepository.findByPostId(post);
		if (comments.isPresent()) {
			return mapEntityListToDtoList(comments.get());
		}
		return null;
	}

	@Override
	public CommentsDTO getCommentById(Integer id) {
		Optional<Comments> comments = commentsRepository.findById(id);
		return commentsMapper.toDto(comments.get());
	}

	@Override
	public List<CommentsDTO> getCommentByName(String name) {
		Optional<List<Comments>> comments = commentsRepository.findByName(name);
		if (comments.isPresent()) {
			return mapEntityListToDtoList(comments.get());
		}
		return null;
	}

	@Override
	public List<CommentsDTO> getCommentByEmail(String email) {
		Optional<List<Comments>> comments = commentsRepository.findByEmail(email);
		if (comments.isPresent()) {
			return mapEntityListToDtoList(comments.get());
		}
		return null;
	}

	@Override
	public List<CommentsDTO> getCommentByBody(String body) {
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
		Posts post = postRepository.findById(postId).get();
		comment.setPostId(post);
		commentsRepository.save(comment);
	}

	@Override
	public void updateComment(Integer id, Integer postId, String name, String body, String email) {
		Comments comment = commentsRepository.findById(id).get();
		saveComment(postId, name, body, email, comment);
	}

	@Override
	public void deleteComment(Integer id) {
		Comments comment = commentsRepository.findById(id).get();
		commentsRepository.delete(comment);
	}

}
