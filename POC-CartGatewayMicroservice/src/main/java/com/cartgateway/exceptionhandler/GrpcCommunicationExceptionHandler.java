package com.cartgateway.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cartgateway.logger.GatewayLogger;

import io.grpc.StatusRuntimeException;

@ControllerAdvice
public class GrpcCommunicationExceptionHandler {
	
	@Autowired GatewayLogger logger;
	
	@ExceptionHandler(StatusRuntimeException.class)
	public void status(StatusRuntimeException e) {
		logger.logThis("Thrown exeception: "+e.getClass()+" "+e.getMessage());
	}
}
