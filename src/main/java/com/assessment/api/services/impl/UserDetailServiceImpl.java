package com.assessment.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.UserDetails;
import com.assessment.api.mapper.UserDetailsMapper;
import com.assessment.api.repository.AddressRepository;
import com.assessment.api.repository.CompanyRepository;
import com.assessment.api.repository.GeoRepository;
import com.assessment.api.repository.UserDetailsRepository;
import com.assessment.api.services.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private GeoRepository geoRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private UserDetailsMapper userDetailsMapper;

	public UserDetails getUserDetailsByUserId(Integer userId) {
		return userDetailsRepository.findById(userId).get();
	}

	public List<UserDetailsDTO> getAllUsers() {
		List<UserDetails> userDetails = userDetailsRepository.findAll();
		if (userDetails.isEmpty()) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}

	@Override
	public UserDetailsDTO getUserById(Integer id) {
		UserDetails userDetails = userDetailsRepository.findById(id).get();
		if (null == userDetails) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}

	@Override
	public UserDetailsDTO getUserByName(String name) {
		UserDetails userDetails = userDetailsRepository.findByName(name).get();
		if (null == userDetails) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}

	@Override
	public UserDetailsDTO getUserByUsername(String username) {
		UserDetails userDetails = userDetailsRepository.findByUsername(username).get();
		if (null == userDetails) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}

	@Override
	public UserDetailsDTO getUserByEmail(String email) {
		UserDetails userDetails = userDetailsRepository.findByEmail(email).get();
		if (null == userDetails) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}

	@Override
	public UserDetailsDTO getUserByPhone(String phone) {
		UserDetails userDetails = userDetailsRepository.findByPhone(phone).get();
		if (null == userDetails) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}

	@Override
	public UserDetailsDTO getUserByWebsite(String website) {
		UserDetails userDetails = userDetailsRepository.findByWebsite(website).get();
		if (null == userDetails) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}
}
