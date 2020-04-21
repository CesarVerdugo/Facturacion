package com.code.facturacion.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private Date timestamp; //fecha y hora que ocuure el error
	private int status;
	private String error;
	private String message;
	private List<String> details;
	private String path;
	
	public ExceptionResponse(HttpStatus status,String message, List<String> details,String path  ) {
		this.timestamp = new Date();
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = message;
		this.details = details;
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
