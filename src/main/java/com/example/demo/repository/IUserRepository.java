package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	public Optional<User> findUserByUsername(String username);
	
}
