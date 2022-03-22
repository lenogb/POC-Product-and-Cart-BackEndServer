package com.cart.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
    private Long productId;
	private String category;	
	private String name;
	private String code;
	private String description;
	private Double price;
	private Long stocks;
	private Date createDate;
	private Date modifiedDate;

	
}
