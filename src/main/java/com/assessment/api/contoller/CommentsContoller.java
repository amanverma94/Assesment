package com.assessment.api.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.dto.CommentsDTO;
import com.assessment.api.services.CommentsService;

@RestController
@RequestMapping(value = "/api/comments/")
public class CommentsContoller {

	@Autowired
	private CommentsService commentsService;

	@GetMapping
	public List<CommentsDTO> getAllComments() {
		return commentsService.getAllComments();
	}

	@GetMapping("{id}")
	public CommentsDTO getCommentById(@PathVariable Integer id) {
		return commentsService.getCommentById(id);
	}

	@GetMapping("post")
	public List<CommentsDTO> getCommentsByPostId(@RequestParam Integer postId) {
		return commentsService.getCommentsByPostId(postId);
	}

	@GetMapping("name")
	public List<CommentsDTO> getCommentByName(@RequestParam String name) {
		return commentsService.getCommentByName(name);
	}

	@GetMapping("body")
	public List<CommentsDTO> getCommentByBody(@RequestParam String body) {
		return commentsService.getCommentByBody(body);
	}

	@PostMapping("add")
	public void addComment(@RequestParam Integer postId, @RequestParam String name, @RequestParam String body,
			@RequestParam String email) {
		if (!StringUtils.isEmpty(postId)) {
			commentsService.addComment(postId, name, body, email);
		}
	}

	@PutMapping
	public void updateComment(@RequestParam Integer id, @RequestParam Integer postId, @RequestParam String name,
			@RequestParam String body, @RequestParam String email) {
		commentsService.updateComment(id, postId, name, body, email);
	}

	@DeleteMapping
	public void deletePost(@RequestParam Integer id) {
		commentsService.deleteComment(id);
	}

}
