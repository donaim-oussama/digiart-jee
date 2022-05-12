package com.digiart;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.digiart.domain.Avatar;
import com.digiart.domain.Publication;
import com.digiart.domain.User;
import com.digiart.repositories.AvatarRepository;
import com.digiart.repositories.CategorieRepository;
import com.digiart.repositories.PublicationRepository;
import com.digiart.repositories.UserRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class PublicationRepositoryTests {
	 @Autowired
	 private TestEntityManager entityManager;
	 @Autowired
	 private PublicationRepository repo;

	 @Test
	 public void testCreate() {
	     Publication publication = new Publication();
	     publication.setTitre("dddsdsdssssdd");
	     publication.setDescription("shksjks");
	     publication.setPrix_init(12);
	     publication.setCategorieid(5);
	     publication.setUsername("amal");
	     
	     
	     
	     Publication savedPublication = repo.save(publication);
	      
			
	     Publication existUser = entityManager.find(Publication.class,savedPublication.getId_publication());
			 
	      
	 }
	 
		/*
		 * @Test public void testFindUserByUsername() { String username = "don"; User
		 * user = repo.findByUsername(username); assertThat(user).isNotNull();
		 * 
		 * }
		 */
	 

}
