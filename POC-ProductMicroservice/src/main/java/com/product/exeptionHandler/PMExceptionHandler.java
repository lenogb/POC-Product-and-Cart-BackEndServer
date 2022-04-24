package com.product.exeptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.exception.ProductException;
import com.product.logger.LogSender;

@ControllerAdvice
public class PMExceptionHandler {

	@Autowired LogSender logger;
	
	@ExceptionHandler(ProductException.class)
	public void status(ProductException e) {
		logger.logThis("Thrown exeception: "+e.getClass()+" "+e.getMessage()+"[Violation: "+e.getErrorfound().getMessage()+"]");
	}
}
