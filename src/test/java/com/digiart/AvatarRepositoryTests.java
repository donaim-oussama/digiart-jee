package com.digiart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.digiart.domain.Avatar;
import com.digiart.domain.Categorie;
import com.digiart.domain.User;
import com.digiart.repositories.AvatarRepository;
import com.digiart.repositories.CategorieRepository;
import com.digiart.repositories.UserRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AvatarRepositoryTests {
	 @Autowired
	 private TestEntityManager entityManager;
	 @Autowired
	 private AvatarRepository repo;
	 @Autowired
	 private CategorieRepository repoC;
	 @Test
	 public void testCreateAvatar() {
	     Avatar avatar = new Avatar();
	     avatar.setId_avatar(2);
	     avatar.setImage_avatar("cheminavatar");
	     
	     Avatar savedAvatar = repo.save(avatar);
	      
	     Avatar existUser = entityManager.find(Avatar.class, savedAvatar.getId_avatar());
	      
	 }
	
	 
		/*
		 * @Test public void testFindUserByUsername() { String username = "don"; User
		 * user = repo.findByUsername(username); assertThat(user).isNotNull();
		 * 
		 * }
		 */
	 

}
