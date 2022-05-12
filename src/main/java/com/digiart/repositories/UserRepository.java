package com.digiart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	 @Query("SELECT u FROM User u WHERE u.username = ?1")
	 public User findByUsername(String username);
	 
	 
	 
	 @Query("SELECT u FROM User u  WHERE u.active = 1 ORDER BY fortune DESC")
	 public List<User> findAndSortByFort();
	 
	 @Query("SELECT u FROM User u WHERE u.active = 1")
	 public List<User> findAllEnabled();
	 
	 

}
