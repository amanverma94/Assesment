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
		if (null == id) {
			return null;
		}
		Optional<Albums> albums = albumsRepository.findById(id);
		if (albums.isPresent()) {
			return albumsMapper.toDto(albums.get());
		}
		return null;
	}

	@Override
	public List<AlbumsDTO> getAlbumsByUserId(Integer userId) {
		if (null == userId) {
			return null;
		}
		UserDetails userDetails = userDetailService.getUserDetailsByUserId(userId);
		Optional<List<Albums>> albums = albumsRepository.findByUser(userDetails);
		if (albums.isPresent()) {
			return mapEntityListToDtoList(albums.get());
		}
		return null;
	}

	@Override
	public List<AlbumsDTO> getAlbumByTitle(String title) {
		if (null == title) {
			return null;
		}
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
		album.setUser(userDetails);

		albumsRepository.save(album);
	}

	@Override
	public void updateAlbum(Integer id, Integer userId, String title) {
		if (null != id) {
			Optional<Albums> album = albumsRepository.findById(id);
			if (album.isPresent()) {
				saveAlbum(userId, title, album.get());
			}
		}
	}

	@Override
	public void deleteAlbum(Integer id) {
		if (null != id) {
			Optional<Albums> album = albumsRepository.findById(id);
			if (album.isPresent()) {
				Optional<List<Photos>> photos = photosRepository.findByAlbum(album.get());
				if (photos.isPresent()) {
					photosRepository.deleteAll(photos.get());
				}
				albumsRepository.delete(album.get());
			}
		}
	}

}
