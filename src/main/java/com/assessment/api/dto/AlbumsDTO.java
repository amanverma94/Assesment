package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */
public class AlbumsDTO {

	private Integer id;
	private String title;
	private Integer userId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AlbumsDTO [id=" + id + ", title=" + title + ", userId=" + userId + "]";
	}
	
	

}
