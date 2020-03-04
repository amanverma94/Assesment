package com.assessment.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.dto.PhotosDTO;
import com.assessment.api.services.PhotosService;

@RestController
@RequestMapping(value = "/api/Photos/")
public class PhotosController {

	@Autowired
	private PhotosService photosService;

	@GetMapping
	public List<PhotosDTO> getAllPhotos() {
		return photosService.getAllPhotos();
	}

	@GetMapping("{id}")
	public PhotosDTO getPhotosById(@PathVariable Integer id) {
		return photosService.getPhotosById(id);
	}

	@GetMapping("album")
	public List<PhotosDTO> getPhotosByAlbumId(@RequestParam Integer albumId) {
		return photosService.getPhotosByAlbumId(albumId);
	}

	@GetMapping("title")
	public List<PhotosDTO> getPhotoByTitle(@RequestParam String title) {
		return photosService.getPhotoByTitle(title);
	}

	@GetMapping("url")
	public List<PhotosDTO> getPhotoByURL(@RequestParam String url) {
		return photosService.getPhotoByURL(url);
	}

	@GetMapping("thumbnail")
	public List<PhotosDTO> getPhotoByThumbnailURL(@RequestParam String thumbnailURL) {
		return photosService.getPhotoByThumbnailURL(thumbnailURL);
	}

	@PostMapping("add")
	public void addPhoto(@RequestParam Integer albumId, @RequestParam String title, @RequestParam String url,
			@RequestParam String thumbnailUrl) {
		if (!StringUtils.isEmpty(albumId)) {
			photosService.addPhoto(albumId, title, url, thumbnailUrl);
		}
	}

	@PutMapping
	public void updateComment(@RequestParam Integer id, @RequestParam Integer albumId, @RequestParam String title,
			@RequestParam String url, @RequestParam String thumbnailUrl) {
		photosService.updatePhoto(id, albumId, title, url, thumbnailUrl);
	}

	@DeleteMapping
	public void deletePhoto(@RequestParam Integer id) {
		photosService.deletePhoto(id);
	}

}
