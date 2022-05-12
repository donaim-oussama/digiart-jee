package com.digiart.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categorie")
public class Categorie {

	@Id
	private Integer id_categorie;
	
	@Column(name = "nom_categorie", nullable = false, length = 45)
	private String nom_categorie;
	

	
	public Integer getId_categorie() {
		return id_categorie;
	}



	public void setId_categorie(Integer id_categorie) {
		this.id_categorie = id_categorie;
	}



	public String getNom_categorie() {
		return nom_categorie;
	}



	public void setNom_categorie(String nom_categorie) {
		this.nom_categorie = nom_categorie;
	}



	
	  public List<Publication> getPublications() { return publications; }
	 


	
	  public void setPublications(List<Publication> publications) {
	  this.publications = publications; }
	 



	
	  @OneToMany(mappedBy="categorie") private List<Publication> publications;
	 
}
