package com.product.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.product.enums.Problem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonFilter("productFilter")
public class ProductResponse {
	
    private Long productId;
	private String category;
	private String name;
	private String code;
	private String description;
	private Double price;
	private String status=Problem.OK.name();
}
