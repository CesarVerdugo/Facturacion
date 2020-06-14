package com.code.facturacion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DocumentNumberAlreadyUsedException extends RuntimeException {

	public DocumentNumberAlreadyUsedException() {
		super("Document Number Already Used");
		// TODO Auto-generated constructor stub
	}

	public DocumentNumberAlreadyUsedException(String documentoNumber) {
		super("Document Number Already Used" + documentoNumber);
		// TODO Auto-generated constructor stub
	}

	
}
