package com.digiart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.digiart.domain.Offre;
import com.digiart.domain.Categorie;
import com.digiart.domain.User;
import com.digiart.repositories.OffreRepository;
import com.digiart.repositories.CategorieRepository;
import com.digiart.repositories.UserRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class OffreRepositoryTests {
	 @Autowired
	 private TestEntityManager entityManager;
	 @Autowired
	 private OffreRepository repo;

	 @Test
	 public void testCreateOffre() {

			 Integer id_offre =40;
			 Offre offre = repo.findOne(id_offre);
			 System.out.println(offre.getPrix());
		  
	   
	      
	 }
	
	 
		/*
		 * @Test public void testFindUserByUsername() { String username = "don"; User
		 * user = repo.findByUsername(username); assertThat(user).isNotNull();
		 * 
		 * }
		 */
	 

}
