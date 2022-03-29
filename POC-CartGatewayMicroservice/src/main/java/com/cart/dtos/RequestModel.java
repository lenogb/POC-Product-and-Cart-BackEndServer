package com.cart.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestModel {

	private Long cartId;
	private Long productId;
	private Long quantity;
	
	@Override
	public String toString() {
		return "Request: (Product Id: " + productId + ", Product quantity: " + quantity + ")";
	}
	
	
}
