package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */
public class PostsDTO {
	private Integer id;
	private String title;
	private String body;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public UserDetailsDTO getUserId() {
		return userId;
	}

	public void setUserId(UserDetailsDTO userId) {
		this.userId = userId;
	}

}
