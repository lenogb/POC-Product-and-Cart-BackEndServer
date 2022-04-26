package com.cartgateway.logging_and_tracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class GatewayLogger {

    Logger logger = LoggerFactory.getLogger(GatewayLogger.class);
    
    public void info(String message) {
    	logger.info(message);
    }
    
    public void error(String message) {
    	logger.error(message);
    }
    
	
}
