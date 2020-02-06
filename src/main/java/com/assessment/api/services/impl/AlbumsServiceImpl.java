package com.assessment.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.api.dto.AlbumsDTO;
import com.assessment.api.entity.Albums;
import com.assessment.api.entity.Photos;
import com.assessment.api.entity.UserDetails;
import com.assessment.api.mapper.AlbumsMapper;
import com.assessment.api.repository.AlbumsRepository;
import com.assessment.api.repository.PhotosRepository;
import com.assessment.api.services.AlbumsService;
import com.assessment.api.services.UserDetailService;

@Service
public class AlbumsServiceImpl implements AlbumsService {

	@Autowired
	private AlbumsRepository albumsRepository;

	@Autowired
	private PhotosRepository photosRepository;

	@Autowired
	private AlbumsMapper albumsMapper;

	@Autowired
	private UserDetailService userDetailService;

	@Override
	public List<AlbumsDTO> getAllAlbums() {
		List<Albums> comments = albumsRepository.findAll();
		if (!comments.isEmpty()) {
			return mapEntityListToDtoList(comments);
		}
		return null;
	}

	private List<AlbumsDTO> mapEntityListToDtoList(List<Albums> albums) {
		List<AlbumsDTO> commentsDTO = new ArrayList<AlbumsDTO>();
		for (Albums album : albums) {
			commentsDTO.add(albumsMapper.toDto(album));
		}
		return commentsDTO;
	}

	@Override
	public AlbumsDTO getAlbumById(Integer id) {
		Optional<Albums> albums = albumsRepository.findById(id);
		return albumsMapper.toDto(albums.get());
	}

	@Override
	public List<AlbumsDTO> getAlbumsByUserId(Integer userId) {
		UserDetails userDetails = userDetailService.getUserDetailsByUserId(userId);
		Optional<List<Albums>> albums = albumsRepository.findByUserId(userDetails);
		if (albums.isPresent()) {
			return mapEntityListToDtoList(albums.get());
		}
		return null;
	}

	@Override
	public List<AlbumsDTO> getAlbumByTitle(String title) {
		Optional<List<Albums>> albums = albumsRepository.findPostByTitle(title);
		if (albums.isPresent()) {
			return mapEntityListToDtoList(albums.get());
		}
		return null;
	}

	@Override
	public void addAlbum(Integer userId, String title) {
		Albums album = new Albums();
		saveAlbum(userId, title, album);
	}

	private void saveAlbum(Integer userId, String title, Albums album) {
		album.setTitle(title);
		UserDetails userDetails = userDetailService.getUserDetailsByUserId(userId);
		album.setUserId(userDetails);

		albumsRepository.save(album);
	}

	@Override
	public void updateAlbum(Integer id, Integer userId, String title) {
		Albums album = albumsRepository.findById(id).get();
		saveAlbum(userId, title, album);
	}

	@Override
	public void deleteAlbum(Integer id) {
		Albums album = albumsRepository.findById(id).get();
		List<Photos> photos = photosRepository.findByAlbumId(album).get();
		photosRepository.deleteAll(photos);
		albumsRepository.delete(album);
	}

}
