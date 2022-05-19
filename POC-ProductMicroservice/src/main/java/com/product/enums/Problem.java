package com.product.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum Problem {
	
	OK(HttpStatus.OK),
	CLIENT_REQUEST(HttpStatus.BAD_REQUEST),
	INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR);
	
	private HttpStatus status;
	
	Problem(HttpStatus status) {
		this.status=status;
	}
}
