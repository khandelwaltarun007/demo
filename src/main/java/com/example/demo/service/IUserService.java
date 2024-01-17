package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.User;

public interface IUserService {

	public List<User> getUsers();
	
	public User createUser(User user);
	
	public Optional<User> getUserById(Long id);
	
	public User updateUser(User user);
	
	public void deleteUser(Long id);
}
