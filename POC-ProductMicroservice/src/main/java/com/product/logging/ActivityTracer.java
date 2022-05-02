package com.product.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ActivityTracer {

	@Autowired LogSender logger;
	
	@Before(value="execution(* com.product.*.*(..))")
	public void runningApplication(JoinPoint jp) {
		logger.logThis("Product Microservice running..");
	}
	
	@Around(value="execution(* com.product.service.*.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
    	
    	String info=	" [ACTIVITY] ";
        String function= jp.getSignature().getName();
        String inputs=	"| Request Inputs: ";
        String returned="| Returned: ";
	    
		Object[] arguments= jp.getArgs();
		
		
		logger.logThis(info+function
				+inputs+Arrays.toString(arguments)
				);
		Object object= jp.proceed();
		logger.logThis("Execution Success. "+returned+object);
		return object;
	}
}
