package com.poc.microservices.product.exceptions;

@SuppressWarnings("serial")
public class AlreadyExistingException extends RuntimeException {

	public AlreadyExistingException(String message) {
		super(message);
	}
}
