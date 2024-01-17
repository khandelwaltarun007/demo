package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Cacheable(value = "users", key = "#id")
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@CachePut(value = "users", key = "#user.id")
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@CacheEvict(value = "users", key = "#id")
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}

}
