package com.assessment.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

	@Value("${external.api.url}")
	private String externalAPI;

	@Autowired
	private RestTemplate restTemplate;

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
		if(userDetails.isEmpty()) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}

	@Override
	public UserDetailsDTO getUserById(Integer id) {
		UserDetails userDetails = userDetailsRepository.findById(id).get();
		if(null==userDetails) {
			return null;
		}
		return userDetailsMapper.toDto(userDetails);
	}
}
