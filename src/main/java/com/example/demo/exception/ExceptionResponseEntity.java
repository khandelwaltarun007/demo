package com.example.demo.exception;

import java.util.Date;

public class ExceptionResponseEntity {
	private Date timestamp;
	private String message;
	private String details;
	private Integer httpStatus;

	public ExceptionResponseEntity(Date timestamp, String message, String details, Integer httpStatus) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.httpStatus = httpStatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	public Integer getHttpStatus() {
		return httpStatus;
	}

}
