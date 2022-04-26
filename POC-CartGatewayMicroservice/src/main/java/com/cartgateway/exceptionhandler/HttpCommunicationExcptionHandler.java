package com.cartgateway.exceptionhandler;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cartgateway.dtos.ErrorResponse;
import com.cartgateway.enums.ServerStatus;
import com.cartgateway.logging_and_tracing.GatewayLogger;

@RestControllerAdvice
public class HttpCommunicationExcptionHandler  {
	
	@Autowired GatewayLogger logger;

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorResponse> catchException(IOException e, WebRequest request) {
		logger.error("Thrown exception: "+e.getClass()+" "+e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(), ServerStatus.SERVER_COMMUNICATION_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ExecutionException.class)
	public ResponseEntity<ErrorResponse> catchException(ExecutionException e, WebRequest request) {
		logger.error("Thrown exeception: "+e.getClass()+" "+e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(e.getCause().toString(), ServerStatus.SERVER_COMMUNICATION_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<ErrorResponse> catchException(InterruptedException e, WebRequest request) {
		logger.error("Thrown exeception: "+e.getClass()+" "+e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(), ServerStatus.SERVER_COMMUNICATION_ERROR.name()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
