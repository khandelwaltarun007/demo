package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private IUserService userService;
	
	@MockBean
	private IUserRepository userRepository;
	
	@BeforeEach
	public void setUp() {
		
	}
	
	@Test
	public void createUser() {
		User user = new User("Admin", "admin@vodafone.com", 989878989L, "admin", "ADMIN", "admin", true);
		Mockito.when(userService.createUser(Mockito.any())).thenReturn(user);
		User created = userRepository.save(user);
		assertEquals(user, created);
	}
	
	@Test
	public void fetchUserById() {
		Optional<User> user = Optional.of(new User("Admin", "admin@vodafone.com", 989878989L, "admin", "ADMIN", "admin", true));
		Mockito.when(userService.getUserById(1L)).thenReturn(user);
		assertEquals(user, userRepository.findById(1L));
	}
	
}
