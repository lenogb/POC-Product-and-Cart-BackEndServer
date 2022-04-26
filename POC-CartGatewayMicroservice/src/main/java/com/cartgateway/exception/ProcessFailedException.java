package com.cartgateway.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;


@Getter
public class ProcessFailedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpStatus status;

	public ProcessFailedException(HttpStatus status, String message){
		super(message);
		this.status=status;
	}
}
