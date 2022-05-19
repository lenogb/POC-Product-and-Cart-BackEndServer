package com.cartgateway.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseInfo {
	
    private Long productId;
	private String name;
	private Double price;
	private Long quantity;
	private String status;
	
	public ResponseInfo(
			@JsonProperty("Product ID") Long productId, 
			@JsonProperty("Product Name") String name, 
			@JsonProperty("Product Price") Double price, 
			@JsonProperty("Quantity") Long quantity,
			String status
			) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.status=status;
	}
	
	
}
