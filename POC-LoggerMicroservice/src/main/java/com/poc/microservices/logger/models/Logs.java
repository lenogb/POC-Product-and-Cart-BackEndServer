package com.poc.microservices.logger.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Document(collection="poc_logs")
@Data
@Component
public class Logs {
	
	@Id
	private String id;
	private String timestamp;
	private String log;

}
