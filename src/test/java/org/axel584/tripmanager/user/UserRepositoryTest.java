package org.axel584.tripmanager.user;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/*
 * Probleme dans ce TU pour charger un mongoDB en mémoire sur windows
 * 
 */

//@ExtendWith(SpringExtension.class)
//@DataMongoTest
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@DisplayName("given user to save"
            + " when save user using MongoDB template"
            + " then collection isn't empty")
	@Test
	public void createUserTest() {
		// given
		User userTest = new User();
		userTest.setEmail("test@test.com");
		userTest.setFirstName("firstNameTest");
		userTest.setLastName("lastNameTest");
		userTest.setPassword("passwordTest");
		userTest.setRegisterDate(new Date());
		
		// when
		userRepository.save(userTest);
		
		//then
		assertThat(userRepository.findAll()).isNotEmpty();
		
		
		
	}
	
}
