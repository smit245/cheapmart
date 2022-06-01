package com.example.demo.test;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user= new User();
		user.setName("smit");
		user.setEmail("Smit@gmail.com");
		user.setPhone("8527416935");
		user.setGender("male");
		user.setCity("Surat");
		user.setPassword("abcd2456");
		
		User savedUser=repo.save(user);
		
		User existUser=entityManager.find(User.class, savedUser.getId()) ;
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
		
		
	}
}