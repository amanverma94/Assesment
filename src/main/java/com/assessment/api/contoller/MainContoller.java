package com.assessment.api.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.dto.PostsDTO;
import com.assessment.api.services.PostsService;

@RestController
@RequestMapping(value="/api/posts")
public class MainContoller {
	
	@Autowired
	private PostsService postsService;
	
	@PostMapping
	public void saveAllPostsToDB() {
		postsService.saveAllPostsToDB();
	}
	
	@GetMapping
	public List<PostsDTO> getAllPosts() {
		return postsService.getAllPosts();
	}
	
	@GetMapping("/userId/{userId}")
	public List<PostsDTO> getAllPostsByUserId(@PathVariable Integer userId) {
		return postsService.getAllPostsByUserId(userId);
	}
	
	@GetMapping("/id/{id}")
	public PostsDTO getPostById(@PathVariable Integer id) {
		return postsService.getPostById(id);
	}
	
	@GetMapping("/title/{title}")
	public List<PostsDTO> getPostByTitleContent(@PathVariable String title) {
		return postsService.getPostByTitleContent(title);
	}
	
	@GetMapping("/body/{body}")
	public List<PostsDTO> getPostByBodyContent(@PathVariable String body) {
		return postsService.getPostByBodyContent(body);
	}

}
