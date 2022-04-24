package com.product.exception;

import org.springframework.http.HttpStatus;

import com.product.enums.InputViolation;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ProductException extends RuntimeException{

	private final HttpStatus apistatus;
	private final InputViolation errorfound;
		
	public ProductException(HttpStatus apistatus,InputViolation errorfound,Object debugmessage) {
		super((String) debugmessage);
		this.apistatus=apistatus;
		this.errorfound=errorfound;
	}
}
