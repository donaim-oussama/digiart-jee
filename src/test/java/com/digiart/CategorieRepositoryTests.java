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
public class CategorieRepositoryTests {
	 @Autowired
	 private TestEntityManager entityManager;
	 @Autowired
	 private AvatarRepository repo;
	 @Autowired
	 private CategorieRepository repoC;

	 @Test
	 public void testCreateCategorie() {
	     Categorie categorie = new Categorie();
	     categorie.setId_categorie(2);
	     categorie.setNom_categorie("Cat1");
	     
	     Categorie savedCategorie = repoC.save(categorie);
	      
			
		Categorie existUser = entityManager.find(Categorie.class,savedCategorie.getId_categorie());
			 
	      
	 }
	 
		/*
		 * @Test public void testFindUserByUsername() { String username = "don"; User
		 * user = repo.findByUsername(username); assertThat(user).isNotNull();
		 * 
		 * }
		 */
	 

}
