package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.service.AuthenticationService;

@RestController
public class AuthenticationResource {

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/api/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody(required = true) AuthenticationRequest credentials) throws Exception {
		AuthenticationResponse authenticationResponse = authenticationService.authenticate(credentials);
		return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
	}
}