package com.digiart.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="commentaire")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_commentaire;
	
	@Column(name = "date_commentaire", nullable = false, length = 45)
	private Date date_commentaire;
	@PrePersist
	private void onCreate() {
		date_commentaire = new Date();
	}
	
	@Column(name = "contenu", nullable = true, length = 100	)
	private String contenu;
	
	@Column(name = "visible", nullable = true)
	private boolean visible;
	
	@ManyToOne
	  @JoinColumn(name="publicationid", insertable=false, updatable=false)	
	  private Publication publication;
	  private Integer publicationid;
	  
	  @ManyToOne
	  @JoinColumn(name="username", insertable=false, updatable=false)	
	  private User user;
	  private String username;
	public Integer getId_commentaire() {
		return id_commentaire;
	}
	public void setId_commentaire(Integer id_commentaire) {
		this.id_commentaire = id_commentaire;
	}
	public Date getDate_commentaire() {
		return date_commentaire;
	}
	public void setDate_commentaire(Date date_commentaire) {
		this.date_commentaire = date_commentaire;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	public Integer getPublicationid() {
		return publicationid;
	}
	public void setPublicationid(Integer publicationid) {
		this.publicationid = publicationid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}