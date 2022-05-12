package com.digiart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.Avatar;
import com.digiart.domain.Categorie;
import com.digiart.domain.User;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
	 @Query("SELECT c FROM Categorie c WHERE c.id_categorie = ?1")
	 public Categorie findById_categorie(Integer id_categorie);
}
