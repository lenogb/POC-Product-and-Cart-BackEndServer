package com.product.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ItemRequest {
	
	private Long productId;
	private Long quantity;
	
	@Override
	public String toString() {
		return "(productId=" + productId + ", quantity=" + quantity+")" ;
	}
	
}
