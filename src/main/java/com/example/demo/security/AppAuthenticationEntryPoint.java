package com.example.demo.security;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exception.ExceptionResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		authException.printStackTrace();
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				authException.getMessage() != null ? authException.getMessage() : "Access Denied.");
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseBody
	public ResponseEntity<Object> accessDenidException(AccessDeniedException ex, WebRequest request) {
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(new Date(),
				"Authorization Failed", ex.getMessage(), HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.FORBIDDEN);
	}
}