package com.anarghya.aayurvedaapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
@RestControllerAdvice
public class CartNotFoundException extends Exception {

	/**
	 * 
	 */

private static final long serialVersionUID = 1L;

	public CartNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(String message) {
		super(message);
	}
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(CartNotFoundException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}
