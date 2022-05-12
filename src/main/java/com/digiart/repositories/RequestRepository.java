package com.digiart.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.Avatar;
import com.digiart.domain.Offre;
import com.digiart.domain.Request;
import com.digiart.domain.User;

public interface RequestRepository extends JpaRepository<Request, Integer> {
	 @Query("SELECT o FROM Request o WHERE o.request_id = ?1")
	 public Request findByRequestID(Integer request_id);
	 
	 @Query("SELECT o FROM Request o WHERE o.username = ?1")
	 public Request findByUsername(String username);
	 
	
}
