package com.cart.configurations;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cart.dto.ExceptionResponse;
import com.cart.enums.RequestError;
import com.cart.exception.CartException;

@Aspect
@Configuration
public class AspectClass {

    @Autowired ExceptionResponse status;
    
    @Around(value="execution(* com.cart.service.CartServiceImpl.*(..))")
	public Object aroundCartService(ProceedingJoinPoint jp) throws Throwable {
    	
		Object[] arguments= jp.getArgs();
		status.setInput(Arrays.toString(arguments));
		
		try {
			return jp.proceed();
		}
		catch(CartException e) {
			status.setDebugmessage(e.getMessage()+" | "+e.getErrorfound().getMessage());
			status.setErrorfound(e.getErrorfound());
			return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
		}
		catch(Throwable e) {
			status.setDebugmessage(e.getMessage());
			status.setErrorfound(RequestError.ERROR);
			return new ResponseEntity<>(status, HttpStatus.NO_CONTENT);
		}
	}
    
    
}
