package com.digiart.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.*;

@Entity
@Table(name="offres")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_offre")
public class Offre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_offre;
   
	@Column(name = "prix", nullable = false, length = 45)
    private float prix;
   
	@Column(name = "date", nullable = false)
    private Date date_offre;
	@PrePersist
	private void onCreate() {
		date_offre = new Date();
	}
   
	@ManyToOne
	@JoinColumn(name="publicationid", insertable=false, updatable=false)
    private Publication publication;
	private Integer publicationid;
   
	public Date getDate_offre() {
		return date_offre;
	}

	public void setDate_offre(Date date_offre) {
		this.date_offre = date_offre;
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

	@ManyToOne
	@JoinColumn(name="username", insertable=false, updatable=false)	
    private User user;
    private String username;
  
   
public int getId_offre() {
	return id_offre;
}

public void setId_offre(int id_offre) {
	this.id_offre = id_offre;
}

public float getPrix() {
	return prix;
}

public void setPrix(float prix) {
	this.prix = prix;
}

public Date getDate() {
	return date_offre;
}

public void setDate(Date date) {
	this.date_offre = date;
}

public Publication getPublication() {
    return publication;
 }

public void setPublication(Publication publication) {
	this.publication = publication;
}

public User getPropose() {
    return user;
 }

public void setPropose(User propose) {
	user = propose;
}

/*public void setPublication(Publication newPublication) {
if (this.publication == null || !this.publication.equals(newPublication))
{
   if (this.publication != null)
   {
      Publication oldPublication = this.publication;
      this.publication = null;
      oldPublication.removeConcerne(this);
   }
   if (newPublication != null)
   {
      this.publication = newPublication;
      this.publication.addConcerne(this);
   }
}
}
*/
   
   /*public void setPropose(User newUser) {
      if (this.Propose == null || !this.Propose.equals(newUser))
      {
         if (this.Propose != null)
         {
            User oldUser = this.Propose;
            this.Propose = null;
            oldUser.removeOffre(this);
         }
         if (newUser != null)
         {
            this.Propose = newUser;
            this.Propose.addOffre(this);
         }
      }
   }*/

}

