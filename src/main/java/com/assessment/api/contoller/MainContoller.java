package com.assessment.api.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/posts")
public class MainContoller {
	
//	@Autowired
//	private PostsService postsService;
	
	@GetMapping
	public void getAllPosts() {
		//postsService.getAllPosts();
	}

}
