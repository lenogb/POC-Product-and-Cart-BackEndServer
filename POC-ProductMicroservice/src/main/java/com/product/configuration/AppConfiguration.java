package com.product.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration	
public class AppConfiguration {
	
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
	@Bean
	public NewTopic creatingTopic() {
		return TopicBuilder.name("PM_activityLogs")
			      .build();
	}
	
}
