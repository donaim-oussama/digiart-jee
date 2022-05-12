package com.digiart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer role_id;
	private String name;
	
	public Integer getId() {
		return role_id;
	}
	public void setId(Integer id) {
		this.role_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
