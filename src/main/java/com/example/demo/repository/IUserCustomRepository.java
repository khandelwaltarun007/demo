package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.User;

public interface IUserCustomRepository {
	
	public Optional<User> getUserByUsername(String username);
}
