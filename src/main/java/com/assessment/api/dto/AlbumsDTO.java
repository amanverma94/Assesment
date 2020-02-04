package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */
public class AlbumsDTO {

	private Integer id;
	private String title;
	private Integer user;

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

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

}
