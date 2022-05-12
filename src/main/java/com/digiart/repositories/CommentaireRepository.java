package com.digiart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.Avatar;
import com.digiart.domain.Commentaire;
import com.digiart.domain.Publication;
import com.digiart.domain.User;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
	 @Query("SELECT c FROM Commentaire c WHERE c.id_commentaire = ?1")
	 public Commentaire findById_commentaire(Integer id_commentaire);
	 
	 List<Commentaire> findAllByPublicationid(Integer publicationid);
}
