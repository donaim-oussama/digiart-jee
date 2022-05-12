package com.digiart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.Avatar;
import com.digiart.domain.User;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
	 @Query("SELECT a FROM Avatar a WHERE a.id_avatar = ?1")
	 public Avatar findById_avatar(Integer id_avatar);
}
