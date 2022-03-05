package com.poc.microservices.product.config;

import java.sql.Timestamp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.poc.microservices.product.util.objectModel;

@Aspect
@Configuration
public class AspectClass {
	@Autowired KafkaTemplate<String, String> kafkaTemplate;
	Long datetime = System.currentTimeMillis();
    	Timestamp timestamp = new Timestamp(datetime);
    	@Autowired objectModel objectModel;

	@Around(value="execution(* com.poc.microservices.product.controller.*.*(..))")
	public void aroundExecutionControllerMethods(ProceedingJoinPoint jp) {
		try {
			Object[] arguments= jp.getArgs();
			objectModel.setAguments(arguments);
			kafkaTemplate.send("PM_activityLogs", "["+timestamp+"]    Client Requesting : "+jp.getSignature().toString()
					+" Request inputs: "+ objectModel.argumentsToString());
			jp.proceed();
			kafkaTemplate.send("PM_activityLogs", "["+timestamp+"]    Execution: SUCCESS, Function-Invoked: "+jp.getSignature().toString());
		}
		catch(Throwable e) {
			kafkaTemplate.send("PM_activityLogs", "["+timestamp+"]    Execution: FAILED, Thrown Exception: true While-running: "+jp.getSignature().toString()
				+" Exception thrown: "+e.getMessage());
		}
	}
}
