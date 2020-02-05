package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.UserDetails;

public interface UserDetailService {

	public UserDetails getUserDetailsByUserId(Integer userId);
	
	public List<UserDetailsDTO> getAllUsers();
	
	public UserDetailsDTO getUserById(Integer id);
	
	public UserDetailsDTO getUserByName(String name);
	
	public UserDetailsDTO getUserByUsername(String username);
	
	public UserDetailsDTO getUserByEmail(String email);
	
	public UserDetailsDTO getUserByPhone(String phne);
	
	public UserDetailsDTO getUserByWebsite(String websit);
	
	
}
