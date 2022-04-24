package com.product.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.product.enums.InputViolation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ExceptionStatusModel {

	private HttpStatus apistatus;
	private InputViolation errorfound;
	private Object debugmessage;
		
	public ExceptionStatusModel(HttpStatus apistatus,InputViolation errorfound,Object debugmessage) {
		this.apistatus=apistatus;
		this.errorfound=errorfound;
		this.debugmessage=debugmessage;
	}
}
