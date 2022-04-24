package com.cartgateway.exceptionhandler;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cartgateway.logger.GatewayLogger;

@ControllerAdvice
public class HttpCommunicationExcptionHandler  {
	
	@Autowired GatewayLogger logger;

	@ExceptionHandler(IOException.class)
	public void catchException(IOException e, WebRequest request) {
		logger.logThis("Thrown exeception: "+e.getClass()+" "+e.getMessage());
	}
	
	@ExceptionHandler(ExecutionException.class)
	public void catchException(ExecutionException e, WebRequest request) {
		logger.logThis("Thrown exeception: "+e.getClass()+" "+e.getMessage());
	}
	
	@ExceptionHandler(InterruptedException.class)
	public void catchException(InterruptedException e, WebRequest request) {
		logger.logThis("Thrown exeception: "+e.getClass()+" "+e.getMessage());
	}
	
}
