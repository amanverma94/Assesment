package com.assessment.api.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AmanVerma
 */
@Entity
@Table(name = "comments")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
		@NamedQuery(name = "Comments.findById", query = "SELECT c FROM Comments c WHERE c.id = :id"),
		@NamedQuery(name = "Comments.findByName", query = "SELECT c FROM Comments c WHERE c.name = :name"),
		@NamedQuery(name = "Comments.findByEmail", query = "SELECT c FROM Comments c WHERE c.email = :email"),
		@NamedQuery(name = "Comments.findByBody", query = "SELECT c FROM Comments c WHERE c.body = :body") })
public class Comments implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "body", length = 500)
	private String body;
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	@ManyToOne
	private Posts postId;

	public Comments() {
	}

	public Comments(Integer id) {
		this.id = id;
	}

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

	public Posts getPostId() {
		return postId;
	}

	public void setPostId(Posts postId) {
		this.postId = postId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Comments)) {
			return false;
		}
		Comments other = (Comments) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entitygenerator.Comments[ id=" + id + " ]";
	}

}
