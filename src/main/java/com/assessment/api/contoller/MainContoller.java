package com.assessment.api.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.dto.PostsDTO;
import com.assessment.api.services.PostsService;

@RestController
@RequestMapping(value = "/api/posts")
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

	@GetMapping("/{id}")
	public PostsDTO getPostById(@PathVariable Integer id) {
		return postsService.getPostById(id);
	}

	@GetMapping("/findByPostId")
	public PostsDTO getPostByPostId(@RequestParam Integer id) {
		return postsService.getPostByPostId(id);
	}

	@GetMapping("/findByUserId")
	public List<PostsDTO> getAllPostsByUserId(@RequestParam Integer userId) {
		return postsService.getAllPostsByUserId(userId);
	}

	@GetMapping("/findByTitle")
	public List<PostsDTO> getPostByTitleContent(@RequestParam String title) {
		return postsService.getPostByTitleContent(title);
	}

	@GetMapping("/findByBody")
	public List<PostsDTO> getPostByBodyContent(@RequestParam String body) {
		return postsService.getPostByBodyContent(body);
	}

}
