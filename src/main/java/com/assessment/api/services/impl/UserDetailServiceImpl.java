package com.assessment.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.api.dto.AddressDTO;
import com.assessment.api.dto.CompanyDTO;
import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.Address;
import com.assessment.api.entity.Company;
import com.assessment.api.entity.Geo;
import com.assessment.api.entity.UserDetails;
import com.assessment.api.mapper.AddressMapper;
import com.assessment.api.mapper.CompanyMapper;
import com.assessment.api.mapper.UserDetailsMapper;
import com.assessment.api.repository.AddressRepository;
import com.assessment.api.repository.AlbumsRepository;
import com.assessment.api.repository.CompanyRepository;
import com.assessment.api.repository.GeoRepository;
import com.assessment.api.repository.PostsRepository;
import com.assessment.api.repository.TodosRepository;
import com.assessment.api.repository.UserDetailsRepository;
import com.assessment.api.services.AlbumsService;
import com.assessment.api.services.PostsService;
import com.assessment.api.services.TodosService;
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
	private AlbumsRepository albumsRepository;

	@Autowired
	private PostsRepository postRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private TodosRepository todosRepository;

	@Autowired
	private UserDetailsMapper userDetailsMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private AlbumsService albumsService;

	@Autowired
	private PostsService postsService;

	@Autowired
	private TodosService todosService;

	public UserDetails getUserDetailsByUserId(Integer userId) {
		if (null == userId) {
			return null;
		}
		return userDetailsRepository.findById(userId).isPresent() ? userDetailsRepository.findById(userId).get() : null;
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
		if (null == id) {
			return null;
		}
		Optional<UserDetails> userDetails = userDetailsRepository.findById(id);
		if (userDetails.isPresent()) {
			return userDetailsMapper.toDto(userDetails.get());
		}
		return null;
	}

	@Override
	public UserDetailsDTO getUserByName(String name) {
		if (null == name) {
			return null;
		}
		Optional<UserDetails> userDetails = userDetailsRepository.findByName(name);
		if (userDetails.isPresent()) {
			return userDetailsMapper.toDto(userDetails.get());
		}
		return null;
	}

	@Override
	public UserDetailsDTO getUserByUsername(String username) {
		if (null == username) {
			return null;
		}
		Optional<UserDetails> userDetails = userDetailsRepository.findByUsername(username);
		if (userDetails.isPresent()) {
			return userDetailsMapper.toDto(userDetails.get());
		}
		return null;
	}

	@Override
	public UserDetailsDTO getUserByEmail(String email) {
		if (null == email) {
			return null;
		}
		Optional<UserDetails> userDetails = userDetailsRepository.findByEmail(email);
		if (userDetails.isPresent()) {
			return userDetailsMapper.toDto(userDetails.get());
		}
		return null;
	}

	@Override
	public UserDetailsDTO getUserByPhone(String phone) {
		if (null == phone) {
			return null;
		}
		Optional<UserDetails> userDetails = userDetailsRepository.findByPhone(phone);
		if (userDetails.isPresent()) {
			return userDetailsMapper.toDto(userDetails.get());
		}
		return null;
	}

	@Override
	public UserDetailsDTO getUserByWebsite(String website) {
		if (null == website) {
			return null;
		}
		Optional<UserDetails> userDetails = userDetailsRepository.findByWebsite(website);
		if (userDetails.isPresent()) {
			return userDetailsMapper.toDto(userDetails.get());
		}
		return null;
	}

	@Override
	public AddressDTO getUserAddressById(Integer userId) {
		if (null == userId) {
			return null;
		}
		UserDetails userDetails = getUserDetailsByUserId(userId);
		if (null != userDetails && null != userDetails.getAddress()) {
			return addressMapper.toDto(userDetails.getAddress());
		}
		return null;
	}

	@Override
	public CompanyDTO getUserCompanyById(Integer userId) {
		if (null == userId) {
			return null;
		}
		UserDetails userDetails = getUserDetailsByUserId(userId);
		if (null != userDetails && null != userDetails.getCompany()) {
			return companyMapper.toDto(userDetails.getCompany());
		}
		return null;
	}

	@Override
	public void addUser(UserDetailsDTO userDetailsDTO) {
		if (null != userDetailsDTO) {
			UserDetails userDetails = userDetailsMapper.toEntity(userDetailsDTO);
			Address addressObj = new Address();
			Geo geo = new Geo();
			Address address = saveAddress(addressObj, geo, userDetailsDTO);
			userDetails.setAddress(address);
			Company companyObj = new Company();
			Company company = saveCompany(companyObj, userDetailsDTO);
			userDetails.setCompany(company);

			userDetailsRepository.save(userDetails);
		}
	}

	private Address saveAddress(Address address, Geo geo, UserDetailsDTO userDetailsDTO) {

		address.setCity(userDetailsDTO.getAddress().getCity());
		address.setSuite(userDetailsDTO.getAddress().getSuite());
		address.setStreet(userDetailsDTO.getAddress().getStreet());
		address.setZipcode(userDetailsDTO.getAddress().getZipcode());
		Geo geos = saveGeo(geo, userDetailsDTO);
		address.setGeo(geos);
		addressRepository.save(address);
		return address;
	}

	private Geo saveGeo(Geo geo, UserDetailsDTO userDetailsDTO) {

		geo.setLat(userDetailsDTO.getAddress().getGeo().getLat());
		geo.setLng(userDetailsDTO.getAddress().getGeo().getLng());
		geoRepository.save(geo);
		return geo;
	}

	private Company saveCompany(Company company, UserDetailsDTO userDetailsDTO) {
		company.setName(userDetailsDTO.getCompany().getName());
		company.setBs(userDetailsDTO.getCompany().getBs());
		company.setCatchPhrase(userDetailsDTO.getCompany().getCatchPhrase());
		companyRepository.save(company);
		return company;
	}

	@Override
	public void editUser(Integer userId, UserDetailsDTO userDetailsDTO) {
		UserDetails userDetails = getUserDetailsByUserId(userId);
		if (null != userDetails) {
			userDetails.setName(userDetailsDTO.getName());
			userDetails.setUsername(userDetailsDTO.getUsername());
			userDetails.setEmail(userDetailsDTO.getEmail());
			userDetails.setPhone(userDetailsDTO.getPhone());
			userDetails.setWebsite(userDetailsDTO.getWebsite());

			Address address = saveAddress(userDetails.getAddress(), userDetails.getAddress().getGeo(), userDetailsDTO);
			userDetails.setAddress(address);

			Company company = saveCompany(userDetails.getCompany(), userDetailsDTO);
			userDetails.setCompany(company);

			userDetailsRepository.save(userDetails);
		}
	}

	@Override
	public void deleteUser(Integer userId) {
		UserDetails userDetails = getUserDetailsByUserId(userId);
		if (null != userDetails) {
			albumsRepository.findByUser(userDetails).ifPresent(availableAlbum -> availableAlbum.stream()
					.forEach(album -> albumsService.deleteAlbum(album.getId())));

			postRepository.findByUser(userDetails).ifPresent(
					availablePosts -> availablePosts.stream().forEach(post -> postsService.deletePost(post.getId())));

			todosRepository.findByUser(userDetails).ifPresent(
					availableTodo -> availableTodo.stream().forEach(todo -> todosService.deleteTodo(todo.getId())));

			userDetailsRepository.delete(userDetails);
			if (null != userDetails.getCompany()) {
				companyRepository.delete(userDetails.getCompany());
			}

			if (null != userDetails.getAddress()) {
				addressRepository.delete(userDetails.getAddress());
				if (null != userDetails.getAddress().getGeo()) {
					geoRepository.delete(userDetails.getAddress().getGeo());
				}

			}

		}
	}

}
