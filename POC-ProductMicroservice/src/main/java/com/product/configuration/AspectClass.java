package com.product.configuration;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import com.product.enums.Errors;
import com.product.exception.ProductException;
import com.product.model.HttpstatusModel;


@Aspect
@Configuration
public class AspectClass {

    @Autowired HttpstatusModel status;
    @Autowired KafkaTemplate<String, String> kafkaTemplate;
    
    String topic=	"PM_activityLogs";
    String request=	" [ACTIVITY] ";
    String function;
    String inputs=	"| Request Inputs: ";
    String success=	"| Result: Success ";
    String failed=	"| Result: Failed ";
    String cause=	"| Cause: ";
    
    
    @Around(value="execution(* com.product.controller.*.*(..))")
	public Object aroundProductController(ProceedingJoinPoint jp) throws Throwable {
    	
    	switch(jp.getSignature().getName()) 
    	{
    	case "getAllProducts": function="Listing Products "; break;
    	case "getProduct": function="Fetching One Product "; break;
    	case "save": function="Saving Product "; break;
    	case "updateProduct": function="Updating Product "; break;
    	case "creatingOrder": function="Processing Order "; break;
    	default: break;
    	}
	    
		Object[] arguments= jp.getArgs();
		
		try {
			//logs: current time | [INFO]
			Object object= jp.proceed();
			kafkaTemplate.send(
					topic,
					request+function+inputs+Arrays.toString(arguments)+" "+success+cause+"No Errors Found");
			return object;
		}
		catch(ProductException e) {
			kafkaTemplate.send(
					topic,
					request+function+inputs+Arrays.toString(arguments)+" "+failed+cause+e.getMessage()+" | "+e.getErrorfound().getMessage());
			status.setApistatus(e.getApistatus());
			status.setDebugmessage(e.getMessage()+" | "+e.getErrorfound().getMessage());
			status.setErrorfound(e.getErrorfound());
			return new ResponseEntity<>(status, e.getApistatus());
		}
		catch(Throwable e) {
			kafkaTemplate.send(
					topic,
					request+function+inputs+Arrays.toString(arguments)+" "+failed+cause+e.getMessage());
			status.setApistatus(HttpStatus.INTERNAL_SERVER_ERROR);
			status.setDebugmessage(e.getMessage());
			status.setErrorfound(Errors.ERROR);
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    
}
