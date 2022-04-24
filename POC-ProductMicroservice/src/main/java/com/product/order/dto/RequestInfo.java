package com.product.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestInfo {

	private Long productId;
    private Long quantity;
    
    public RequestInfo(
    		@JsonProperty("Product ID") Long productId,
    		@JsonProperty("Quantity") Long quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
    
    
}
