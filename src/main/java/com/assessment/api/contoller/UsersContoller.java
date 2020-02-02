package com.assessment.api.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.services.UserDetailService;

@RestController
@RequestMapping(value = "/api/users")
public class UsersContoller {

	@Autowired
	private UserDetailService userDetailService;

	
	@PostMapping
	public void saveAllUsersToDB() {
		userDetailService.saveAllUsersToDB();
	}


}
