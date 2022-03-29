package com.cart.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

	@Bean 
	public String errorForInput() {
		return "Error found for input: ";
	}
	
}
