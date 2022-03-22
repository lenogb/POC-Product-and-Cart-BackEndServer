package com.product.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductResponse {

	private Long productId;
	private String category;
	private String name;
	private String code;
	private String description;
	private Double price;
	private Long stocks;
}
