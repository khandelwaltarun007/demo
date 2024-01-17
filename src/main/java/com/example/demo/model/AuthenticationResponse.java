package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;
	private Date expirationDate;

	public AuthenticationResponse(String jwtToken, Date expirationDate) {
		super();
		this.token = jwtToken;
		this.expirationDate = expirationDate;
	}

	public String getJwtToken() {
		return token;
	}

	public void setJwtToken(String jwtToken) {
		this.token = jwtToken;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}