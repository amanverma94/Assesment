package com.assessment.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.api.dto.PhotosDTO;
import com.assessment.api.entity.Albums;
import com.assessment.api.entity.Photos;
import com.assessment.api.mapper.PhotosMapper;
import com.assessment.api.repository.AlbumsRepository;
import com.assessment.api.repository.PhotosRepository;
import com.assessment.api.services.PhotosService;

@Service
public class PhotosServiceImpl implements PhotosService {

	@Value("${external.api.url}")
	private String externalAPI;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private PhotosRepository photosRepository;

	@Autowired
	private AlbumsRepository albumsRepository;

	@Autowired
	private PhotosMapper photosMapper;

	@Override
	public List<PhotosDTO> getAllPhotos() {
		List<Photos> comments = photosRepository.findAll();
		if (!comments.isEmpty()) {
			return mapEntityToDtoList(comments);
		}
		return null;
	}

	private List<PhotosDTO> mapEntityToDtoList(List<Photos> Photos) {
		List<PhotosDTO> commentsDTO = new ArrayList<PhotosDTO>();
		for (Photos Photo : Photos) {
			commentsDTO.add(photosMapper.toDto(Photo));
		}
		return commentsDTO;
	}

	@Override
	public PhotosDTO getPhotosById(Integer id) {
		Optional<Photos> Photos = photosRepository.findById(id);
		return photosMapper.toDto(Photos.get());
	}

	@Override
	public List<PhotosDTO> getPhotosByAlbumId(Integer albumId) {
		Albums album = albumsRepository.findById(albumId).get();
		Optional<List<Photos>> Photos = photosRepository.findByAlbumId(album);
		if (Photos.isPresent()) {
			return mapEntityToDtoList(Photos.get());
		}
		return null;
	}

	@Override
	public List<PhotosDTO> getPhotoByTitle(String title) {
		Optional<List<Photos>> Photos = photosRepository.findByTitle(title);
		if (Photos.isPresent()) {
			return mapEntityToDtoList(Photos.get());
		}
		return null;
	}

	@Override
	public List<PhotosDTO> getPhotoByURL(String url) {
		Optional<List<Photos>> Photos = photosRepository.findByURL(url);
		if (Photos.isPresent()) {
			return mapEntityToDtoList(Photos.get());
		}
		return null;
	}

	@Override
	public List<PhotosDTO> getPhotoByThumbnailURL(String thumbnailUrl) {
		Optional<List<Photos>> Photos = photosRepository.findByThumbnailURL(thumbnailUrl);
		if (Photos.isPresent()) {
			return mapEntityToDtoList(Photos.get());
		}
		return null;
	}

	@Override
	public void addPhoto(Integer albumId, String title, String url, String thumbnailUrl) {
		Photos photo = new Photos();
		savePhoto(albumId, title, url, thumbnailUrl, photo);
	}

	private void savePhoto(Integer albumId, String title, String url, String thumbnailUrl, Photos photo) {
		photo.setTitle(title);
		photo.setUrl(url);
		photo.setThumbnailUrl(thumbnailUrl);
		Albums album = albumsRepository.findById(albumId).get();
		photo.setAlbumId(album);
		photosRepository.save(photo);
	}

	@Override
	public void updatePhoto(Integer id, Integer albumId, String title, String url, String thumbnailUrl) {
		Photos photo = photosRepository.findById(id).get();
		savePhoto(albumId, title, url, thumbnailUrl, photo);
	}

	@Override
	public void deletePhoto(Integer id) {
		Photos Photo = photosRepository.findById(id).get();
		photosRepository.delete(Photo);

	}

}
