package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.AddressDTO;
import com.assessment.api.dto.CompanyDTO;
import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.UserDetails;

public interface UserDetailService {

	public UserDetails getUserDetailsByUserId(Integer userId);

	public List<UserDetailsDTO> getAllUsers();

	public UserDetailsDTO getUserById(Integer id);

	public UserDetailsDTO getUserByName(String name);

	public UserDetailsDTO getUserByUsername(String username);

	public UserDetailsDTO getUserByEmail(String email);

	public UserDetailsDTO getUserByPhone(String phone);

	public UserDetailsDTO getUserByWebsite(String website);

	public AddressDTO getUserAddressById(Integer userId);

	public CompanyDTO getUserCompanyById(Integer userId);

	public void addUser(UserDetailsDTO userDetailsDTO);

	public void editUser(Integer userId, UserDetailsDTO userDetailsDTO);
	
	public void deleteUser(Integer userId);

}
