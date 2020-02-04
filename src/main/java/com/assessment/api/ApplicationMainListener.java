package com.assessment.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.assessment.api.JsonMapper.AlbumsBO;
import com.assessment.api.JsonMapper.CommentsBO;
import com.assessment.api.JsonMapper.PhotosBO;
import com.assessment.api.JsonMapper.PostsBO;
import com.assessment.api.JsonMapper.TodosBO;
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
		System.out.println("createDB>>>>> " + createDB);
		if (createDB.equalsIgnoreCase("create")) {
			saveUsersToDB();
			saveTodosToDB();
			savePostsToDB();
			saveCommentsToDB();
			saveAlbumsToDB();
			savePhotosToDB();
		}
	}

	private void saveUsersToDB() {
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
		ResponseEntity<PostsBO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/posts", PostsBO[].class);
		List<PostsBO> postsBOList = Arrays.asList(responseEntity.getBody());
		for (PostsBO obj : postsBOList) {
			Posts posts = new Posts();
			posts.setBody(obj.getBody());
			posts.setTitle(obj.getTitle());
			UserDetails userDetails = userDetailService.getUserDetailsByUserId(obj.getUserId());
			posts.setUserId(userDetails);
			postRepository.save(posts);
		}

	}

	private void saveCommentsToDB() {
		ResponseEntity<CommentsBO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/comments",
				CommentsBO[].class);
		List<CommentsBO> commentBOList = Arrays.asList(responseEntity.getBody());
		for (CommentsBO commentDTO : commentBOList) {
			Comments comments = new Comments();
			comments.setBody(commentDTO.getBody());
			comments.setEmail(commentDTO.getEmail());
			comments.setName(commentDTO.getName());
			Posts post = postRepository.findById(commentDTO.getPostId()).get();
			comments.setPostId(post);
			commentsRepository.save(comments);
		}
	}

	private void saveAlbumsToDB() {
		ResponseEntity<AlbumsBO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/albums",
				AlbumsBO[].class);
		List<AlbumsBO> albumsBOList = Arrays.asList(responseEntity.getBody());
		for (AlbumsBO albumsBO : albumsBOList) {
			Albums albums = new Albums();
			albums.setTitle(albumsBO.getTitle());
			UserDetails userDetails = userDetailService.getUserDetailsByUserId(albumsBO.getUserId());
			albums.setUserId(userDetails);
			albumsRepository.save(albums);
		}
	}

	private void savePhotosToDB() {
		ResponseEntity<PhotosBO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/photos",
				PhotosBO[].class);
		List<PhotosBO> photosBOList = Arrays.asList(responseEntity.getBody());
		for (PhotosBO photosBO : photosBOList) {
			Photos photos = new Photos();
			photos.setTitle(photosBO.getTitle());
			photos.setThumbnailUrl(photosBO.getThumbnailUrl());
			photos.setUrl(photosBO.getUrl());
			Albums album = albumsRepository.findById(photosBO.getAlbumId()).get();
			photos.setAlbumId(album);
			photosRepository.save(photos);
		}
	}

	private void saveTodosToDB() {
		ResponseEntity<TodosBO[]> responseEntity = restTemplate.getForEntity(externalAPI + "/todos", TodosBO[].class);
		List<TodosBO> todosBOList = Arrays.asList(responseEntity.getBody());
		for (TodosBO todosBO : todosBOList) {
			Todos todo = new Todos();
			todo.setTitle(todosBO.getTitle());
			todo.setCompleted(todosBO.getCompleted());
			UserDetails userDetails = userDetailService.getUserDetailsByUserId(todosBO.getUserId());
			todo.setUserId(userDetails);
			todosRepository.save(todo);
		}
	}

}
