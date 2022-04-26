package com.cartgateway.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cartgateway.dtos.ErrorResponse;
import com.cartgateway.logging_and_tracing.GatewayLogger;

import io.grpc.StatusRuntimeException;

@RestControllerAdvice
public class GrpcCommunicationExceptionHandler {
	
	@Autowired GatewayLogger logger;
	
	@ExceptionHandler(StatusRuntimeException.class)
	public ResponseEntity<ErrorResponse> status(StatusRuntimeException e) {
		logger.error("Thrown exeception: "+e.getClass()+" "+e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getStatus().toString()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
