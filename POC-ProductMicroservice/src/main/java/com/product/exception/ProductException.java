package com.product.exception;

import org.springframework.http.HttpStatus;

import com.product.enums.Errors;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ProductException extends RuntimeException{

	private final HttpStatus apistatus;
	private final Errors errorfound;
		
	public ProductException(HttpStatus apistatus,Errors errorfound,Object debugmessage) {
		super((String) debugmessage);
		this.apistatus=apistatus;
		this.errorfound=errorfound;
	}
}
