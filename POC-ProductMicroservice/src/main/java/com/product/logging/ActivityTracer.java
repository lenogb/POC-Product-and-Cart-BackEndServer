package com.product.logging;

import java.util.Arrays;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ActivityTracer {

	@Autowired LogSender logger;
	StopWatch timer;
	
	ActivityTracer(){
		this.timer=new StopWatch();
	}
	
	@Around(value="execution(* com.product.controller.*.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
    	
    	String info=	" [ACTIVITY] ";
        String function= jp.getSignature().getName();
        String inputs=	"| Request Inputs: ";
        String returned="| Returned: ";
	    
		Object[] arguments= jp.getArgs();
		
		
		logger.logThis(info+"Executing "+function+" method "
				+inputs+Arrays.toString(arguments)
				);
		timer.reset();
		timer.start();
		Object object= jp.proceed();
		timer.stop();
		logger.logThis("Execution Success. "+returned+object+" | Execution consumed-time: "+timer.getTime()+"ms");
		return object;
	}
	
}
