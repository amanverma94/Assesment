package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */
public class PhotosDTO {

	private Integer id;
	private String title;
	private String url;
	private String thumbnailUrl;
	private Integer album;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public Integer getAlbum() {
		return album;
	}

	public void setAlbum(Integer album) {
		this.album = album;
	}

}
