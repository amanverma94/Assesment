package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.AlbumsDTO;

public interface AlbumsService {

	public List<AlbumsDTO> getAllAlbums();

	public AlbumsDTO getAlbumById(Integer id);

	public List<AlbumsDTO> getAlbumsByUserId(Integer userId);

	public List<AlbumsDTO> getAlbumByTitle(String title);

	public void addAlbum(Integer userId, String title);

	public void updateAlbum(Integer id, Integer userId, String title);

	public void deleteAlbum(Integer id);

}
