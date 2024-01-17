package com.example.demo.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;

import jakarta.annotation.PostConstruct;

@Repository
public class SuperUserRepository {

	@Autowired
	private IUserRepository userRepository;

	@PostConstruct
	@Transactional
	public void createSuperUser() {
		Optional<User> optionalUser = userRepository.findById(Long.valueOf(1));
		User existingUser = optionalUser.orElse(null);
		if (existingUser == null) {
			User user = new User("Admin", "admin@vodafone.com", 989878989L, "admin", "ADMIN", "admin", true);
			userRepository.save(user);
		}
	}

}