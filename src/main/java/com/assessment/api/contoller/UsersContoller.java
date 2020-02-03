package com.assessment.api.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.services.UserDetailService;

@RestController
@RequestMapping(value = "/api/users")
public class UsersContoller {

	@Autowired
	private UserDetailService userDetailService;

	@GetMapping
	public void getAllUsers() {
		
	}
	
	

}
