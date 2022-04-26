package com.cartgateway.logging_and_tracing;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
@Configuration
public class ActivityTracer {
	
	@Autowired GatewayLogger logger;

	@Around(value="execution(* com.cartgateway.controller.*.*(..))")
	public Object aroundController(ProceedingJoinPoint jp) throws Throwable {
    	
		String request=	"[ACTIVITY-Controller] ";
	    String function= jp.getSignature().getName();
	    String inputs=	"| Request Inputs: ";
	    String success=	"| Result: Success ";
	    String cause=	"| Cause: ";
	    
		Object[] arguments= jp.getArgs();
		logger.info(request+function+inputs+Arrays.toString(arguments));
		Object obj = jp.proceed();
		logger.info(request+function+" "+success+cause+"No Errors Found. | Returned:"+obj);
		return obj;
	}
	
	@Around(value="execution(* com.cartgateway.servercomm.*.*.*(..))")
	public Object aroundServerCommunicator(ProceedingJoinPoint jp) throws Throwable {
    	
		String request=	"[ACTIVITY-Server Communication";
	    String function= jp.getSignature().getName();
	    String inputs=	"| Request Inputs: ";
	    String success=	"| Result: Success ";
	    String cause=	"| Cause: ";
	    
		Object[] arguments= jp.getArgs();
		logger.info(request+function+inputs+Arrays.toString(arguments));
		Object obj = jp.proceed();
		logger.info(request+function+" "+success+cause+"No Errors Found. | Returned:"+obj);
		return obj;
	}
}
