package com.cart.dto;

import org.springframework.stereotype.Component;

import com.cart.enums.RequestError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ExceptionResponse {

	private RequestError errorfound;
	private Object debugmessage;
	private String input;
		
	
}
