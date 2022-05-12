package com.digiart.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.Avatar;
import com.digiart.domain.Offre;
import com.digiart.domain.User;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
	 @Query("SELECT o FROM Offre o WHERE o.id_offre = ?1")
	 public Offre findById_offre(Integer id_offre);
	 
	 @Query("SELECT o FROM Offre o WHERE publicationid=?1 AND o.prix=(SELECT MAX(prix) FROM Offre WHERE publicationid=?1) ") 
	 public Offre findOne(Integer publicationid);
	 
	 List<Offre> findAllByPublicationid(Integer publicationid, Sort sort);
}
