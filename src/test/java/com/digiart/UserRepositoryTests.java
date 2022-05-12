package com.digiart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.digiart.domain.User;
import com.digiart.repositories.UserRepository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	 @Autowired
	 private TestEntityManager entityManager;
	 @Autowired
	 private UserRepository repo;
	 @Test
	 public void testCreateUser() {
	     User user = new User();
	     user.setUsername("dhjddsdsdhjhjhd");
	     user.setEmail("s@gmsddsjljsdlsddsawdsdsdsdwwwwl.com");
	     user.setMot_de_passe("123");
	     user.setNum_tele("12488516");
	     user.setIdCarte("1574447525");
	     user.setNom("BAKACHE");
	     user.setPrenom("Youness");
	     user.setFortune(12);
	     User savedUser = repo.save(user);
	      
	
	 }
	 
	
	 

}
