package com.product.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ProductException extends RuntimeException{

	private final HttpStatus apistatus;
	private final String status;
		
	public ProductException(HttpStatus apistatus,String status,Object debugmessage) {
		super((String) debugmessage);
		this.apistatus=apistatus;
		this.status=status;
	}
}
