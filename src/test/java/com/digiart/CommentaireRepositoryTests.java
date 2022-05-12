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
import com.digiart.domain.Commentaire;
import com.digiart.domain.User;
import com.digiart.repositories.AvatarRepository;
import com.digiart.repositories.CategorieRepository;
import com.digiart.repositories.CommentaireRepository;
import com.digiart.repositories.UserRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CommentaireRepositoryTests {
	 @Autowired
	 private TestEntityManager entityManager;
	 @Autowired
	 private CommentaireRepository repo;

	 @Test
	 public void testCreate() {
	     Commentaire commentaire = new Commentaire();
	     commentaire.setId_commentaire(1);
	     commentaire.setContenu("Nice");
	     commentaire.setPublicationid(1);
	     commentaire.setUsername("amal");
	     

	     
	     
	     
	     Commentaire savedCommentaire = repo.save(commentaire);
	      
			
		Commentaire existUser = entityManager.find(Commentaire.class,savedCommentaire.getId_commentaire());
			 
	      
	 }
	 
		/*
		 * @Test public void testFindUserByUsername() { String username = "don"; User
		 * user = repo.findByUsername(username); assertThat(user).isNotNull();
		 * 
		 * }
		 */
	 

}
