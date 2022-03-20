package com.product.exception;

import org.springframework.http.HttpStatus;

import com.product.enums.RequestError;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ProductException extends RuntimeException{

	private final HttpStatus apistatus;
	private final RequestError errorfound;
		
	public ProductException(HttpStatus apistatus,RequestError errorfound,Object debugmessage) {
		super((String) debugmessage);
		this.apistatus=apistatus;
		this.errorfound=errorfound;
	}
}
