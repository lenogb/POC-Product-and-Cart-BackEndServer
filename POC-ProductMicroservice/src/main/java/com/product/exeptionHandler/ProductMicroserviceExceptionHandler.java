package com.product.exeptionHandler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.dto.ErrorResponse;
import com.product.exception.ProductException;
import com.product.logging.LogSender;

@RestControllerAdvice
public class ProductMicroserviceExceptionHandler {

	@Autowired LogSender log;
	@Autowired ModelMapper mapper;
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorResponse> status(ProductException e) {
		log.logThis("Execution Failed. Thrown exception: "+e.getClass()+" "+e.getMessage()+"[Cause: "+e.getCause()+"]");
		return new ResponseEntity<>(
				new ErrorResponse(e.getMessage(),e.getStatus()),e.getApistatus());
	}
	
//	@ExceptionHandler(NoResultException.class)
//	public ResponseEntity<Object> status(NoResultException e, WebRequest request) {
//		log.logThis("Execution Failed. Thrown exception: "+e.getClass()+" "+e.getMessage());
//		return new ResponseEntity<>(
//				new ErrorResponse(e.getCause().toString(),ServerStatus.DATABASE_RESULT_ERROR.name()),
//				HttpStatus.NOT_FOUND);
//	}
}
