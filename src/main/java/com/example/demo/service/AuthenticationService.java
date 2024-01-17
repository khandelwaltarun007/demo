package com.example.demo.service;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;

public interface AuthenticationService {

	public AuthenticationResponse authenticate(AuthenticationRequest credentials) throws Exception;
}
