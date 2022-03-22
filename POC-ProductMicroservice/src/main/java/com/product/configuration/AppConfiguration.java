package com.product.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer{

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public NewTopic creatingTopic() {
		return TopicBuilder.name("PM_activityLogs")
			      .build();
	}
	
	@Bean 
	public String errorForInput() {
		return "Error found for input: ";
	}
}
