package com.poc.microservices.activityLogger.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Document(collection="product_logger")
@Data
@Component
public class Logs {
	
	@Id
	private String log;

}
