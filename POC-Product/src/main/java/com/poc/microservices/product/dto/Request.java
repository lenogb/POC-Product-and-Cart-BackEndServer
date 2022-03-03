package com.poc.microservices.product.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class Request {
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@NotNull
	@Min(100)
	private Double price;
	@NotNull(message="Empty stocks")
	private Integer stocks;
}
