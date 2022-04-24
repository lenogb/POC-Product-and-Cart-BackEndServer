package com.product.logger;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.product.dto.ExceptionStatusModel;


@Aspect
@Configuration
@Component
public class LogSender {

    @Autowired ExceptionStatusModel status;
    @Autowired KafkaTemplate<String, String> kafkaTemplate;
    
    String topic=	"PM_activityLogs";
    
    public void logThis(String message) {
    	kafkaTemplate.send(
				topic,
				message);
    }
    
    @Around(value="execution(* com.product.controller.*.*(..))")
	public ResponseEntity<Object> aroundProductController(ProceedingJoinPoint jp) throws Throwable{
    	
    	String info=	" [ACTIVITY] ";
        String function= jp.getSignature().getName();
        String inputs=	"| Request Inputs: ";
        String success=	"| Result: Success ";
        String cause=	"| Cause: ";
        String returned="| Returned: ";
	    
		Object[] arguments= jp.getArgs();
		
		kafkaTemplate.send(
			topic,
			info+function+inputs+Arrays.toString(arguments));
		Object object= jp.proceed();
		kafkaTemplate.send(
			topic,
			info+function+success+cause+"No Errors Found"+returned+object);
		return new ResponseEntity<>(object, HttpStatus.OK);
	}
}
