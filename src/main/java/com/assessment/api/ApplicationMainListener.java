package com.assessment.api;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.assessment.api.dto.AlbumsDTO;
import com.assessment.api.dto.CommentsDTO;
import com.assessment.api.dto.PhotosDTO;
import com.assessment.api.dto.PostsDTO;
import com.assessment.api.dto.TodosDTO;
import com.assessment.api.dto.UserDetailsDTO;
import com.assessment.api.entity.Address;
import com.assessment.api.entity.Albums;
import com.assessment.api.entity.Comments;
import com.assessment.api.entity.Company;
import com.assessment.api.entity.Geo;
import com.assessment.api.entity.Photos;
import com.assessment.api.entity.Posts;
import com.assessment.api.entity.Todos;
import com.assessment.api.entity.UserDetails;
import com.assessment.api.mapper.AddressMapper;
import com.assessment.api.mapper.CompanyMapper;
import com.assessment.api.mapper.GeoMapper;
import com.assessment.api.mapper.UserDetailsMapper;
import com.assessment.api.repository.AddressRepository;
import com.assessment.api.repository.AlbumsRepository;
import com.assessment.api.repository.CommentsRepository;
import com.assessment.api.repository.CompanyRepository;
import com.assessment.api.repository.GeoRepository;
import com.assessment.api.repository.PhotosRepository;
import com.assessment.api.repository.PostsRepository;
import com.assessment.api.repository.TodosRepository;
import com.assessment.api.repository.UserDetailsRepository;
import com.assessment.api.services.UserDetailService;

@Component
public class ApplicationMainListener implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${external.api.url}")
	private String externalAPI;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String createDB;

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
	private PostsRepository postRepository;

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private AlbumsRepository albumsRepository;

	@Autowired
	private PhotosRepository photosRepository;

	@Autowired
	private TodosRepository todosRepository;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private UserDetailsMapper userDetailsMapper;

	@Autowired
	private GeoMapper geoMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (createDB.equalsIgnoreCase("create")) {
			saveUsersToDB();
			savePostsToDB();
			saveCommentsToDB();
			saveAlbumsToDB();
			saveTodosToDB();
			savePhotosToDB();
		}
	}

	private void saveUsersToDB() {
		System.out.println("Inserting Users data to database...");
		ResponseEntity<UserDetailsDTO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/users",
				UserDetailsDTO[].class);
		List<UserDetailsDTO> userDetailList = Arrays.asList(responseEntity.getBody());
		for (UserDetailsDTO dto : userDetailList) {
			UserDetails userDetails = userDetailsMapper.toEntity(dto);

			Address adressEntity = saveAddress(dto);
			userDetails.setAddress(adressEntity);

			Company companyEntity = saveCompany(dto);
			userDetails.setCompany(companyEntity);
			userDetailsRepository.save(userDetails);
		}
		System.out.println("Users data inserted into database.");
	}

	private Company saveCompany(UserDetailsDTO obj) {
		if (null == obj.getCompany()) {
			return null;
		}
		Company company = companyMapper.toEntity(obj.getCompany());
		return companyRepository.save(company);
	}

	private Address saveAddress(UserDetailsDTO obj) {
		if (null == obj.getAddress()) {
			return null;
		}
		Address address = addressMapper.toEntity(obj.getAddress());
		Geo geoEntity = saveGeo(obj);
		address.setGeo(geoEntity);
		return addressRepository.save(address);
	}

	private Geo saveGeo(UserDetailsDTO obj) {
		if (null == obj.getAddress().getGeo()) {
			return null;
		}
		Geo geo = geoMapper.toEntity(obj.getAddress().getGeo());
		return geoRepository.save(geo);
	}

	private void savePostsToDB() {
		System.out.println("Inserting Posts data to database...");
		ResponseEntity<PostsDTO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/posts", PostsDTO[].class);
		List<PostsDTO> postsList = Arrays.asList(responseEntity.getBody());
		for (PostsDTO obj : postsList) {
			Posts posts = new Posts();
			posts.setBody(obj.getBody());
			posts.setTitle(obj.getTitle());
			UserDetails userDetails = userDetailService.getUserDetailsByUserId(obj.getUserId());
			posts.setUser(userDetails);
			postRepository.save(posts);
		}
		System.out.println("Posts data inserted into database.");
	}

	private void saveCommentsToDB() {
		System.out.println("Inserting Comments data to database...");
		ResponseEntity<CommentsDTO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/comments",
				CommentsDTO[].class);
		List<CommentsDTO> commentsList = Arrays.asList(responseEntity.getBody());
		for (CommentsDTO commentDTO : commentsList) {
			Comments comments = new Comments();
			comments.setBody(commentDTO.getBody());
			comments.setEmail(commentDTO.getEmail());
			comments.setName(commentDTO.getName());
			if (null != commentDTO.getPostId()) {
				Optional<Posts> post = postRepository.findById(commentDTO.getPostId());
				if (post.isPresent()) {
					comments.setPost(post.get());
				}
			}
			commentsRepository.save(comments);
		}
		System.out.println("Comments data inserted into database.");
	}

	private void saveAlbumsToDB() {
		System.out.println("Inserting Albums data to database...");
		ResponseEntity<AlbumsDTO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/albums",
				AlbumsDTO[].class);
		List<AlbumsDTO> albumsList = Arrays.asList(responseEntity.getBody());
		for (AlbumsDTO album : albumsList) {
			Albums albums = new Albums();
			albums.setTitle(album.getTitle());
			UserDetails userDetails = userDetailService.getUserDetailsByUserId(album.getUserId());
			albums.setUser(userDetails);
			albumsRepository.save(albums);
		}
		System.out.println("Albums data inserted into database.");
	}

	private void savePhotosToDB() {
		System.out.println("Inserting Photos data to database...");
		ResponseEntity<PhotosDTO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/photos",
				PhotosDTO[].class);
		List<PhotosDTO> photosList = Arrays.asList(responseEntity.getBody());
		for (PhotosDTO photosBO : photosList) {
			Photos photos = new Photos();
			photos.setTitle(photosBO.getTitle());
			photos.setThumbnailUrl(photosBO.getThumbnailUrl());
			photos.setUrl(photosBO.getUrl());
			if (null != photosBO.getAlbumId()) {
				Optional<Albums> album = albumsRepository.findById(photosBO.getAlbumId());
				if (album.isPresent()) {
					photos.setAlbum(album.get());
				}
			}
			photosRepository.save(photos);
		}
		System.out.println("Photos data inserted into database.");
	}

	private void saveTodosToDB() {
		System.out.println("Inserting Todos data to database...");
		ResponseEntity<TodosDTO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/todos", TodosDTO[].class);
		List<TodosDTO> todosList = Arrays.asList(responseEntity.getBody());
		for (TodosDTO todosBO : todosList) {
			Todos todo = new Todos();
			todo.setTitle(todosBO.getTitle());
			todo.setCompleted(todosBO.getCompleted());
			UserDetails userDetails = userDetailService.getUserDetailsByUserId(todosBO.getUserId());
			todo.setUser(userDetails);
			todosRepository.save(todo);
		}
		System.out.println("Todos data inserted into database.");
	}

}
