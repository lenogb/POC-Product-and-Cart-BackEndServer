package com.cart.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer{


	@Bean 
	public String errorForInput() {
		return "Error found for input: ";
	}
	
}
