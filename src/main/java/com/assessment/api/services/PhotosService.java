package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.PhotosDTO;

public interface PhotosService {

	public List<PhotosDTO> getAllPhotos();

	public PhotosDTO getPhotosById(Integer id);

	public List<PhotosDTO> getPhotosByAlbumId(Integer albumId);

	public List<PhotosDTO> getPhotoByTitle(String title);
	
	public List<PhotosDTO> getPhotoByURL(String url);
	
	public List<PhotosDTO> getPhotoByThumbnailURL(String thumbnailURL);

	public void addPhoto(Integer albumId, String title, String url,String thumbnailUrl);

	public void updatePhoto(Integer id, Integer albumId, String title, String url,String thumbnailUrl);

	public void deletePhoto(Integer id);

}
