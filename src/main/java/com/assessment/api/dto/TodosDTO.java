package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */

public class TodosDTO {

	private Integer id;
	private String title;
	private Boolean completed;
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

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public UserDetailsDTO getUserId() {
		return userId;
	}

	public void setUserId(UserDetailsDTO userId) {
		this.userId = userId;
	}

}
