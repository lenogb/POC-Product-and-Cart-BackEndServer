package com.product.exeptionHandler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.dto.ErrorResponse;
import com.product.enums.Problem;
import com.product.exception.ProductException;
import com.product.logging.LogSender;

@RestControllerAdvice
public class ProductMicroserviceExceptionHandler {

	@Autowired LogSender log;
	ModelMapper mapper = new ModelMapper();
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorResponse> status(ProductException e) {
		log.logThis("Execution Failed. Threw exception: "+e.getClass()+" "+e.getMessage()+"[Cause: "+e.getCause()+"]");
		ErrorResponse response = new ErrorResponse(e.getMessage(),Problem.CLIENT_REQUEST);
		return new ResponseEntity<>(
				response,response.getProblem().getStatus());
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponse> status(Throwable e) {
		log.logThis("Execution Failed. Threw exception: "+e.getClass()+" "+e.getMessage()+"[Cause: "+e.getCause()+"]");
		ErrorResponse response = new ErrorResponse("Under Maintenance",Problem.INTERNAL_SERVER);
		return new ResponseEntity<>(response,response.getProblem().getStatus());
	}

}
