package com.poc.microservices.activityLogger.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class ConsumerConfig {

	//variable that will hold the bootstap server url
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
			
	public Map<String, Object> consumerConfiguration(){
		Map<String, Object> property= new HashMap<>();
		property.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);				
		property.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		property.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return property;
	}
			
	//producer factory which responsible for creating producer instances
	//allows us to create kafka producers
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfiguration());
	}
			
	//container that receives all the messages from a topic
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(ConsumerFactory<String, String> consumerFactory){
		ConcurrentKafkaListenerContainerFactory<String, String> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}
}
