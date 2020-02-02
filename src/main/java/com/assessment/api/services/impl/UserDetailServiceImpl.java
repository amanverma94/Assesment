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

	@Override
	public void saveAllUsersToDB() {
		ResponseEntity<UserDetailsDTO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/users",
				UserDetailsDTO[].class);
		List<UserDetailsDTO> userDetailList = Arrays.asList(responseEntity.getBody());
		for (UserDetailsDTO obj : userDetailList) {
			UserDetails userDetails = new UserDetails();
			userDetails.setName(obj.getName());
			userDetails.setUsername(obj.getUsername());
			userDetails.setEmail(obj.getEmail());
			userDetails.setPhone(obj.getPhone());
			userDetails.setWebsite(obj.getWebsite());

			Address address = new Address();
			address.setStreet(obj.getAddress().getStreet());
			address.setSuite(obj.getAddress().getSuite());
			address.setCity(obj.getAddress().getCity());
			address.setZipcode(obj.getAddress().getZipcode());

			Geo geo = new Geo();
			geo.setLat(obj.getAddress().getGeo().getLat());
			geo.setLng(obj.getAddress().getGeo().getLng());
			Geo geoEntity = geoRepository.save(geo);
			address.setGeo(geoEntity);
			Address adressEntity = addressRepository.save(address);
			userDetails.setAddress(adressEntity);

			Company company = new Company();
			company.setName(obj.getCompany().getName());
			company.setCatchPhrase(obj.getCompany().getCatchPhrase());
			company.setBs(obj.getCompany().getBs());
			Company companyEntity = companyRepository.save(company);
			userDetails.setCompany(companyEntity);
			userDetailsRepository.save(userDetails);
		}

	}

	public UserDetails getUserDetailsByUserId(Integer userId) {
		return userDetailsRepository.findById(userId).get();
	}
}
