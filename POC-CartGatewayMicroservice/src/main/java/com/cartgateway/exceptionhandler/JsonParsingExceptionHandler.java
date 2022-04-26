package com.cartgateway.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cartgateway.logging_and_tracing.GatewayLogger;
import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class JsonParsingExceptionHandler {
	
	@Autowired GatewayLogger logger;
	
	@ExceptionHandler(JsonProcessingException.class)
	public void catchException(JsonProcessingException e) {
		logger.error("Thrown exeception: "+e.getClass()+" "+e.getMessage());
	}

}
