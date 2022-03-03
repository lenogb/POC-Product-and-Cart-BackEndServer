package com.poc.microservices.product.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Response {

	private long productId;
	private String name;
	private String description;
	private Double price;
}
