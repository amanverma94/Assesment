package com.assessment.api.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.services.UserDetailService;

@RestController
@RequestMapping(value = "/api/users/")
public class UsersContoller {

	@Autowired
	private UserDetailService userDetailService;

	@GetMapping
	public List<UserDetailsDTO> getAllUsers() {
		return userDetailService.getAllUsers();
	}
	
	@GetMapping("{id}")
	public UserDetailsDTO getUserById(@PathVariable Integer id) {
		return userDetailService.getUserById(id);
	}

	@GetMapping("name")
	public UserDetailsDTO getUserByName(@RequestParam String name) {
		return userDetailService.getUserByName(name);
	}
	
	@GetMapping("username")
	public UserDetailsDTO getUserByUsername(@RequestParam String username) {
		return userDetailService.getUserByUsername(username);
	}
	
	@GetMapping("email")
	public UserDetailsDTO getUserByEmail(@RequestParam String email) {
		return userDetailService.getUserByEmail(email);
	}
	
	@GetMapping("phone")
	public UserDetailsDTO getUserByPhone(@RequestParam String phone) {
		return userDetailService.getUserByPhone(phone);
	}
	
	@GetMapping("website")
	public UserDetailsDTO getUserByWebsite(@RequestParam String website) {
		return userDetailService.getUserByWebsite(website);
	}
}
