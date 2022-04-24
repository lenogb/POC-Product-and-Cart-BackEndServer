package com.cartgateway.logger;

import java.util.Arrays;
//import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Configuration
@Component
public class GatewayLogger {

    
    Logger logger = LoggerFactory.getLogger(GatewayLogger.class);
    
    public void logThis(String message) {
    	logger.info(message);
    }
    
	@Around(value="execution(* com.cartgateway.controller.*.*(..))")
	public ResponseEntity<Object> aroundProductController(ProceedingJoinPoint jp) throws Throwable {
    	
		String request=	"[ACTIVITY] ";
	    String function= jp.getSignature().getName();
	    String inputs=	"| Request Inputs: ";
	    String success=	"| Result: Success ";
	    String cause=	"| Cause: ";
	    
		Object[] arguments= jp.getArgs();
		logger.info(request+function+inputs+Arrays.toString(arguments));
		Object obj = jp.proceed();
		logger.info(request+function+" "+success+cause+"No Errors Found. | Returned:"+obj);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
}
