package com.example.demo.resource;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;

@RestController
public class UserResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

	private static final String USER_DOESNOT_EXIST_EXCEPTION_MSG = "User Id does not exist.";
	@Autowired
	private IUserService userService;

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/user")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createUser = userService.createUser(user);
		LOGGER.info("User has been created with user id : " + createUser.getId());
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PatchMapping("/user/{id}/status/{status}")
	public ResponseEntity<String> updateUserStatus(@PathVariable("id") Long id, @PathVariable("status") int status) {
		Optional<User> user = userService.getUserById(id);
		if (user.isPresent()) {
			user.get().setStatus(status == 0 ? false : true);
			userService.updateUser(user.get());
			LOGGER.info("Status of user with user id : " + id + " has been changed!");
			return ResponseEntity.ok("User status has been updated!");
		} else {
			LOGGER.error("Provided user id :" + id + " does not exist.");
			throw new EntityNotFoundException(USER_DOESNOT_EXIST_EXCEPTION_MSG);
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		LOGGER.info("User details has been updated with user id : " + user.getId());
		return ResponseEntity.ok(userService.updateUser(user));
	}

	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException(USER_DOESNOT_EXIST_EXCEPTION_MSG));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
		LOGGER.info("User id :" + id + " has been deleted.");
		userService.deleteUser(id);
		return ResponseEntity.ok("User id :" + id + " has been deleted.");
	}
}
