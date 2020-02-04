package com.assessment.api.contoller;

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

import com.assessment.api.dto.AlbumsDTO;
import com.assessment.api.services.AlbumsService;

@RestController
@RequestMapping(value = "/api/albums/")
public class AlbumsContoller {

	@Autowired
	private AlbumsService albumsService;

	@GetMapping
	public List<AlbumsDTO> getAllAlbums() {
		return albumsService.getAllAlbums();
	}

	@GetMapping("{id}")
	public AlbumsDTO getCommentById(@PathVariable Integer id) {
		return albumsService.getAlbumById(id);
	}

	@GetMapping("user")
	public List<AlbumsDTO> getAlbumsByUserId(@RequestParam Integer userId) {
		return albumsService.getAlbumsByUserId(userId);
	}

	@GetMapping("title")
	public List<AlbumsDTO> getAlbumByTitle(@RequestParam String title) {
		return albumsService.getAlbumByTitle(title);
	}

	@PostMapping("add")
	public void addAlbum(@RequestParam Integer userId, @RequestParam String title) {
		if (!StringUtils.isEmpty(userId)) {
			albumsService.addAlbum(userId, title);
		}
	}

	@PutMapping
	public void updateComment(@RequestParam Integer id, @RequestParam Integer userId, @RequestParam String title) {
		albumsService.updateAlbum(id, userId, title);
	}

	@DeleteMapping
	public void deletePost(@RequestParam Integer id) {
		albumsService.deleteAlbum(id);
	}

}
