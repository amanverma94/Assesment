package com.assessment.api.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.Address;
import com.assessment.api.entity.Company;
import com.assessment.api.entity.Geo;
import com.assessment.api.entity.UserDetails;
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

	public UserDetails getUserDetailsByUserId(Integer userId) {
		return userDetailsRepository.findById(userId).get();
	}
}
