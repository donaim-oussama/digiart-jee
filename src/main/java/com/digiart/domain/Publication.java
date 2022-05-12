package com.digiart.domain;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="publication")
public class Publication {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id_publication;
   
   @Column(name = "titre", nullable = false, length = 45)
   private String titre;
   @Column(name = "description", nullable = false, length = 45)
   private String description;
   @Column(name = "prix_init", nullable = true)
   private float prix_init;

@Column(name = "date_publication", nullable = false)
   private Date date_publication;
   @Column(name = "duration", nullable = true)
   private int duration;
   
   public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
@PrePersist
	private void onCreate() {
	   date_publication = new Date();
	}
   
   @Column(nullable = false)
   private boolean vendu = false;
   
   @Column(name = "image", nullable = true,  length = 64)
   private String image;
   

	
	  public List<Offre> getOffres() {
	return offres;
}
public void setOffres(List<Offre> offres) {
	this.offres = offres;
}

	@OneToMany(mappedBy="publication",  cascade = CascadeType.ALL) 
	  private List<Commentaire> commentaires;
	  @OneToMany(mappedBy="publication",  cascade = CascadeType.ALL)
	   private List<Offre> offres;
	 
	
	  @ManyToOne
	  @JoinColumn(name="categorieid", insertable=false, updatable=false) private
	  Categorie categorie; 
	  public List<Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	private Integer categorieid;
	 
	
	  @ManyToOne
	  @JoinColumn(name="username", insertable=false, updatable=false) 
	  private User user; 
	  private String username;
	  
	  @ManyToOne
	  @JoinColumn(name="ownedby", insertable=false, updatable=false)	
	  private User owner;
	  private String ownedby;
	  
	 
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getOwnedby() {
		return ownedby;
	}
	public void setOwnedby(String ownedby) {
		this.ownedby = ownedby;
	}
	@Transient
	    public String getPhotosImagePath() {
	        if (image == null || id_publication == null) return null;
	         
	        return "/images/" + image;
	    }
	 
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public String getDescription() {
	return description;
}
public Integer getId_publication() {
	return id_publication;
}
public Categorie getCategorie() {
	return categorie;
}
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}
public Integer getCategorieid() {
	return categorieid;
}
public void setCategorieid(Integer categorieid) {
	this.categorieid = categorieid;
}
public void setId_publication(Integer id_publication) {
	this.id_publication = id_publication;
}
public void setDescription(String description) {
	this.description = description;
}
public float getPrix_init() {
	return prix_init;
}
public void setPrix_init(float prix_init) {
	this.prix_init = prix_init;
}
public Date getDate_publication() {
	return date_publication;
}
public void setDate_publication(Date date_publication) {
	this.date_publication = date_publication;
}
public boolean isVendu() {
	return vendu;
}
public void setVendu(boolean vendu) {
	this.vendu = vendu;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}

  public User getUser() { return user; } public void setUser(User user) {
  this.user = user; } public String getUsername() { return username; } public
  void setUsername(String username) { this.username = username; }
 

  
}
