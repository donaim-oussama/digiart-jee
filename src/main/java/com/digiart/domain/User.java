package com.digiart.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="users")
public class User {
	
		@Id
		private String username;
		
		@Column(name = "first_name", nullable = false, length = 45)
		private String nom;
		
		@Column(name = "last_name", nullable = false, length = 45) 
		private String prenom;
		@Column(nullable = false, unique = true, length = 45)
		private String idCarte;
		@Column(nullable = false,length = 45)
		private String num_tele;
		@Column(nullable = false, unique = true, length = 45)
		private String email;
		@Column(nullable = false, length = 45)
		private String mot_de_passe;
		@Column(nullable = false, length = 45)
		private double fortune = 0.0;
		@Column(nullable = false)
		private boolean active = true;
		
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@OnDelete(action = OnDeleteAction.CASCADE)
		@JoinTable(name ="users_roles", joinColumns = @JoinColumn (name="user_id"), inverseJoinColumns = @JoinColumn (name="role_id"))
		private Set<Role> roles=new HashSet<>();

		

		
		
	
		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}

		@OneToMany(mappedBy="user", cascade = CascadeType.ALL) private List<Offre> offres; 
		  
		  @OneToMany(mappedBy="user", cascade = CascadeType.ALL) private List<Commentaire> commentaires;
		 
		 @OneToMany(mappedBy="user", cascade = CascadeType.ALL) private List<Publication> publications; 
		 
		@ManyToOne
		@JoinColumn(name="avatarid", nullable=true, insertable=false, updatable=false)
	    private Avatar avatar;
		private Integer avatarid;
		
		

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getIdCarte() {
			return idCarte;
		}

		public void setIdCarte(String idCarte) {
			this.idCarte = idCarte;
		}

		public String getNum_tele() {
			return num_tele;
		}

		public void setNum_tele(String num_tele) {
			this.num_tele = num_tele;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMot_de_passe() {
			return mot_de_passe;
		}

		public void setMot_de_passe(String mot_de_passe) {
			this.mot_de_passe = mot_de_passe;
		}

		public double getFortune() {
			return fortune;
		}

		
		
		  public List<Offre> getOffres() { return offres; }
		  
		  public void setOffres(List<Offre> offres) { this.offres = offres; }
		 
		  
		  public List<Commentaire> getCommentaires() { return commentaires; }
		  
		  public void setCommentaires(List<Commentaire> commentaires) {
		  this.commentaires = commentaires; }
		 
		  public List<Publication> getPublications() { return publications; }
		  
		  public void setPublications(List<Publication> publications) {
		  this.publications = publications; }
		 

		public Avatar getAvatar() {
			return avatar;
		}

		public void setAvatar(Avatar avatar) {
			this.avatar = avatar;
		}

		public Integer getAvatarid() {
			return avatarid;
		}

		public void setAvatarid(Integer avatarid) {
			this.avatarid = avatarid;
		}

		public void setFortune(double fortune) {
			this.fortune = fortune;
		}

		
		

}


