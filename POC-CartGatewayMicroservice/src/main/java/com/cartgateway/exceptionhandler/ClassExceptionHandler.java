package com.cartgateway.exceptionhandler;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cartgateway.dtos.ErrorResponse;
import com.cartgateway.enums.Problem;
import com.cartgateway.exception.ProcessFailedException;
import com.cartgateway.logging_and_tracing.GatewayLogger;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

@RestControllerAdvice
public class ClassExceptionHandler {
	
	@Autowired GatewayLogger logger;
	
	//Exception thrown during grpc calls
	@ExceptionHandler(StatusRuntimeException.class)
	public ResponseEntity<Object> status(StatusRuntimeException e) {
		logger.error("Threw exception: "+e.getClass()+" "+e.getStatus().getDescription()+" "+e.getCause());
		if(e.getStatus().getCode().equals(Status.INVALID_ARGUMENT.getCode()))
		{
			return new ResponseEntity<>(
					new ErrorResponse(e.getStatus().getDescription(),Problem.CLIENT_REQUEST), Problem.CLIENT_REQUEST.getStatus());
		}
		
		return new ResponseEntity<>(
				new ErrorResponse("Under Maintenance",Problem.INTERNAL_SERVER), Problem.INTERNAL_SERVER.getStatus());
	}
	
	//
	@ExceptionHandler(ProcessFailedException.class)
	public ResponseEntity<ErrorResponse> status(ProcessFailedException e) {
		logger.error("Threw exeception: "+e.getClass()+" "+e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(),Problem.CLIENT_REQUEST), Problem.CLIENT_REQUEST.getStatus());
	}
	
	
	@ExceptionHandler({
		IOException.class, 
		ExecutionException.class, 
		InterruptedException.class,
		JsonProcessingException.class})
	public ResponseEntity<Object> status(Throwable e) {
		logger.error("Threw exception: "+e.getClass()+" "+e.getMessage()+" "+e.getCause());
		
		return new ResponseEntity<>(
				new ErrorResponse("Under Maintenance",Problem.INTERNAL_SERVER), Problem.INTERNAL_SERVER.getStatus());
	}
}
