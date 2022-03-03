package com.poc.microservices.product.exceptions;

public class EmptyFieldException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyFieldException(String message) {
		super(message);
	}

}
