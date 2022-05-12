package com.digiart.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.Avatar;
import com.digiart.domain.Publication;
import com.digiart.domain.User;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {
	 @Query("SELECT p FROM Publication p WHERE p.id_publication = ?1")
	 public Publication findById_publication(Integer id_publication);
	 
	 @Query("SELECT p FROM Publication p WHERE p.duration > 0")
	 List<Publication> findLiveAuctions();
	 
	 
	 
	 
	 List<Publication> findByUsername(String username);
	 
	 List<Publication> findByCategorieid(Integer categorieid);
	 
	 @Query("SELECT p FROM Publication p WHERE titre like %?1%")
	 Page<Publication> findByTitre(String titre, Pageable pageable);

	 @Query("SELECT COUNT(p) FROM Publication p WHERE p.username=?1")
     long countPubPerUser(String username);

	public List<Publication> findTop4ByUsername(String username, Pageable paging);
	 
	 @Query("SELECT p FROM Publication p WHERE p.username IN (SELECT username FROM User u WHERE u.active = 1 ) ORDER BY p.date_publication DESC")
	 public List<Publication> findAllpubs();
	 
}
