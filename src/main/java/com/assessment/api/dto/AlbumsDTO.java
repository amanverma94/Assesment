package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */
public class AlbumsDTO {

	private Integer id;
	private String title;
	private UserDetailsDTO userId;

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

	public UserDetailsDTO getUserId() {
		return userId;
	}

	public void setUserId(UserDetailsDTO userId) {
		this.userId = userId;
	}

}
