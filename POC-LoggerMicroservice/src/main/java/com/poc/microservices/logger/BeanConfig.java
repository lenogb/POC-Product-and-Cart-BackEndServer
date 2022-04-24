package com.poc.microservices.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

//	@Bean
//	public Logger logger() throws SecurityException, IOException {
//		Logger logger = Logger.getLogger("Logger"); 
//		FileHandler fh = new FileHandler("./src/main/resources/Logs.log");  
//	    logger.addHandler(fh);
//	    SimpleFormatter formatter = new SimpleFormatter();  
//	    fh.setFormatter(formatter);  
//	    return logger;
//	}
}
