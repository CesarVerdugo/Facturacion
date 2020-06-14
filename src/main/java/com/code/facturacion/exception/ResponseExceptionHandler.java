package com.code.facturacion.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors, request.getContextPath());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmailAlreadyUsedException.class)
	public final ResponseEntity<Object> handleFieldAlreadyUsedExceptionException(EmailAlreadyUsedException ex, WebRequest request)
			throws Exception {
		HttpStatus status = ex.getClass().getAnnotation(ResponseStatus.class).value();
		ExceptionResponse exceptionResponse = new ExceptionResponse(status, ex.getMessage(), Arrays.asList(ex.getMessage()),request.getContextPath());
		return new ResponseEntity<Object>(exceptionResponse, status);
	}
	
	@ExceptionHandler(DocumentNumberAlreadyUsedException.class)
	public final ResponseEntity<Object> handleFieldAlreadyUsedExceptionException(DocumentNumberAlreadyUsedException ex, WebRequest request)
			throws Exception {
		HttpStatus status = ex.getClass().getAnnotation(ResponseStatus.class).value();
		ExceptionResponse exceptionResponse = new ExceptionResponse(status, ex.getMessage(), Arrays.asList(ex.getMessage()),request.getContextPath());
		return new ResponseEntity<Object>(exceptionResponse, status);
	}
}
