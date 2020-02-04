package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.UserDetails;

public interface UserDetailService {

	public UserDetails getUserDetailsByUserId(Integer userId);
	
	public List<UserDetailsDTO> getAllUsers();
	
	public UserDetailsDTO getUserById(Integer id);
}
