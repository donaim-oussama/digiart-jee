package com.digiart.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="avatar")
public class Avatar {
	
	public Integer getId_avatar() {
		return id_avatar;
	}


	public void setId_avatar(Integer id_avatar) {
		this.id_avatar = id_avatar;
	}


	public String getImage_avatar() {
		return image_avatar;
	}


	public void setImage_avatar(String image_avatar) {
		this.image_avatar = image_avatar;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	@Id
	private Integer id_avatar;
	
	@Column(name = "image_avatar", nullable = false, length = 45)
	private String image_avatar;
	

	@OneToMany(mappedBy="avatar")
	private List<User> users;
}
