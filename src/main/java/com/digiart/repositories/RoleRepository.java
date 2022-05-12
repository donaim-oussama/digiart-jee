package com.digiart.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digiart.domain.Avatar;
import com.digiart.domain.Offre;
import com.digiart.domain.Request;
import com.digiart.domain.Role;
import com.digiart.domain.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	 @Query("SELECT o FROM Role o WHERE o.role_id = ?1")
	 public Role findByRoleId(Integer role_id);
}
