package com.cartgateway.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cartgateway.dtos.ErrorResponse;
import com.cartgateway.enums.ServerStatus;
import com.cartgateway.exception.ProcessFailedException;
import com.cartgateway.logging_and_tracing.GatewayLogger;

@RestControllerAdvice
public class RequestsExceptionHandler {

@Autowired GatewayLogger logger;
	
	@ExceptionHandler(ProcessFailedException.class)
	public ResponseEntity<ErrorResponse> status(ProcessFailedException e) {
		logger.error("Thrown exeception: "+e.getClass()+" "+e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(),ServerStatus.REQUEST_INVALID.name()), e.getStatus());
	}
}
