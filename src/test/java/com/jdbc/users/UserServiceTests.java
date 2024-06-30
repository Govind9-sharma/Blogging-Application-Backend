package com.jdbc.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.jdbc.users.dtos.CreateUserRequest;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
	
	@Autowired
	UserService service;
	
//	@MockBean
//	UserRepository repository;
	
	@Test
	void can_create_users()
	{
		var user=service.createUser(new CreateUserRequest("Govind Sharma","1234","sharmagovind@gmail.com"));
		Assertions.assertNotNull(user);
		Assertions.assertEquals("Govind Sharma", user.getUsername());
	}
}
