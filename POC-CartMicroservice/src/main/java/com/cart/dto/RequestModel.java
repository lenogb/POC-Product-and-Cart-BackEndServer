package com.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestModel {

	private Long cartId;
	private Long productId;
	private String quantity;
	
	@Override
	public String toString() {
		return "Request: (Product Id: " + productId + ", Product quantity: " + quantity + ")";
	}
	
	
}
