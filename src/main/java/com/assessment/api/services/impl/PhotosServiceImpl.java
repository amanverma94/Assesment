package com.assessment.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.api.dto.PhotosDTO;
import com.assessment.api.entity.Albums;
import com.assessment.api.entity.Photos;
import com.assessment.api.mapper.PhotosMapper;
import com.assessment.api.repository.AlbumsRepository;
import com.assessment.api.repository.PhotosRepository;
import com.assessment.api.services.PhotosService;

@Service
public class PhotosServiceImpl implements PhotosService {

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

	private List<PhotosDTO> mapEntityToDtoList(List<Photos> photos) {
		List<PhotosDTO> photosDTO = new ArrayList<PhotosDTO>();
		photos.stream().map(photo -> photosMapper.toDto(photo)).forEach(photo -> photosDTO.add(photo));
		return photosDTO;
	}

	@Override
	public PhotosDTO getPhotosById(Integer id) {
		if (null == id) {
			return null;
		}
		Optional<Photos> photos = photosRepository.findById(id);
		if (photos.isPresent()) {
			return photosMapper.toDto(photos.get());
		}
		return null;
	}

	@Override
	public List<PhotosDTO> getPhotosByAlbumId(Integer albumId) {
		if (null == albumId) {
			return null;
		}
		Optional<Albums> album = albumsRepository.findById(albumId);
		if (album.isPresent()) {
			Optional<List<Photos>> Photos = photosRepository.findByAlbum(album.get());
			if (Photos.isPresent()) {
				return mapEntityToDtoList(Photos.get());
			}
		}
		return null;
	}

	@Override
	public List<PhotosDTO> getPhotoByTitle(String title) {
		if (null == title) {
			return null;
		}
		Optional<List<Photos>> Photos = photosRepository.findByTitle(title);
		if (Photos.isPresent()) {
			return mapEntityToDtoList(Photos.get());
		}
		return null;
	}

	@Override
	public List<PhotosDTO> getPhotoByURL(String url) {
		if (null == url) {
			return null;
		}
		Optional<List<Photos>> Photos = photosRepository.findByURL(url);
		if (Photos.isPresent()) {
			return mapEntityToDtoList(Photos.get());
		}
		return null;
	}

	@Override
	public List<PhotosDTO> getPhotoByThumbnailURL(String thumbnailUrl) {
		if (null == thumbnailUrl) {
			return null;
		}
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
		if (null != albumId) {
			Optional<Albums> album = albumsRepository.findById(albumId);
			if (album.isPresent()) {
				photo.setAlbum(album.get());
			}
		}
		photosRepository.save(photo);
	}

	@Override
	public void updatePhoto(Integer id, Integer albumId, String title, String url, String thumbnailUrl) {
		if (null != id) {
			Optional<Photos> photo = photosRepository.findById(id);
			if (photo.isPresent()) {
				savePhoto(albumId, title, url, thumbnailUrl, photo.get());
			}
		}
	}

	@Override
	public void deletePhoto(Integer id) {
		if (null != id) {
			Optional<Photos> photo = photosRepository.findById(id);
			if (photo.isPresent()) {
				photosRepository.delete(photo.get());
			}
		}
	}

}
