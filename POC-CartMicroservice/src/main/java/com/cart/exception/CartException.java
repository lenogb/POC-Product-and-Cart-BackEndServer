package com.cart.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class CartException extends RuntimeException{
		
	public CartException(String debugmessage) {
		super(debugmessage);
	}
}