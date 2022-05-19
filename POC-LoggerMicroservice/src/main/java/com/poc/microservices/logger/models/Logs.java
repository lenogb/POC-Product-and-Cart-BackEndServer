package com.poc.microservices.logger.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="poc_logs")
@Data
public class Logs {
	
	@Id
	private String id;
	private String timestamp;
	private String log;

}
