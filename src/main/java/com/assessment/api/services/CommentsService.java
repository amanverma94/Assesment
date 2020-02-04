package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.CommentsDTO;

public interface CommentsService {

	public List<CommentsDTO> getAllComments();

	public List<CommentsDTO> getCommentsByPostId(Integer posId);

	public CommentsDTO getCommentById(Integer id);

	public List<CommentsDTO> getCommentByName(String name);

	public List<CommentsDTO> getCommentByEmail(String email);

	public List<CommentsDTO> getCommentByBody(String body);

	public void addComment(Integer postId, String name, String body, String email);

	public void updateComment(Integer id, Integer postId, String name, String body, String email);

	public void deleteComment(Integer id);

}
