package com.product.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestInfo {
	
	private Long productId;
    private Long quantity;
    
    public RequestInfo(
    		 Long productId,
    		 Long quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
    
    
}
