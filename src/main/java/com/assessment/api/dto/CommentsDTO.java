package com.assessment.api.dto;

/**
 *
 * @author AmanVerma
 */
public class CommentsDTO {

	private Integer id;
	private String name;
	private String email;
	private String body;
	private PostsDTO postId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public PostsDTO getPostId() {
		return postId;
	}

	public void setPostId(PostsDTO postId) {
		this.postId = postId;
	}

}
