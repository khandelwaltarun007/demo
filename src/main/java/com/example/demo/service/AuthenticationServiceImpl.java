package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.security.JwtUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyAppUserDetailService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;
	
	/*
	 * @Autowired
	 *///private IUserCustomRepository userCustomRepository;
	
	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest credentials) throws Exception {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password." + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		final UserDetails userDetails = userDetailService.loadUserByUsername(username);
		final String token = jwtUtil.generateToken(userDetails);
		//System.out.println(userCustomRepository.getUserByUsername(jwtUtil.extractUserName(token)));
		return new AuthenticationResponse(token, jwtUtil.getTokenExpirationDate(token));
	}

}
