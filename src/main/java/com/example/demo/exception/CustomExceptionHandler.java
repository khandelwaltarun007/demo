package com.example.demo.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> errors = new ArrayList<>();
		for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.add("Validation Error found in " + fieldError.getField() + " : " + fieldError.getDefaultMessage());
		}

		for (final ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
			errors.add(objectError.getObjectName() + " : " + objectError.getDefaultMessage());
		}
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(new Date(), "Validation Failed",
				errors.toString(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, WebRequest request)
			throws IOException {
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(new Date(), "Invalid Request",
				ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<Object>(exceptionResponseEntity, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public void GenericException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	@ExceptionHandler(value = { AccessDeniedException.class })
	public ResponseEntity<Object> accessDeniedException(AccessDeniedException ex, WebRequest request) {
		ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(new Date(), "Access Denied",
				ex.getMessage(), HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<>(exceptionResponseEntity, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

}
