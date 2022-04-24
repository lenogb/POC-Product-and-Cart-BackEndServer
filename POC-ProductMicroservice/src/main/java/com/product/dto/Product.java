package com.product.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonFilter("productFilter")
public class Product {
	
    private Long productId;
	private String category;
	private String name;
	private String code;
	private String description;
	private Double price;
	@JsonIgnore
	private Long stocks;
	@JsonIgnore
	private Long booked;
	@JsonIgnore
	private Long checkedout;
	private Long available;
	@JsonIgnore
	private Date modifiedDate;
}
