package com.code.facturacion.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyUsedException extends RuntimeException{

	public EmailAlreadyUsedException() {
		super(" Email Already Used ");
		// TODO Auto-generated constructor stub
	}

	public EmailAlreadyUsedException(String email) {
		super(" Email Already Used  " + email);
		// TODO Auto-generated constructor stub
	}

	
}
