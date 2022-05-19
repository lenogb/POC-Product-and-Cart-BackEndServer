package com.product.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class ProductException extends RuntimeException{

	public ProductException(String debugmessage) {
		super(debugmessage);
	}
}
