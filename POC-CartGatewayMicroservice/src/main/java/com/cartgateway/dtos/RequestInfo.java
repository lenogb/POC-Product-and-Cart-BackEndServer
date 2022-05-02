package com.cartgateway.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestInfo {
	
	@NotNull(message="product id should be provided. Found unprovided")
	private Long productId;
	
	@NotNull(message="how many of the product specified you wish to get?. Quantity is not provided. Please provide one.")
	@Min(value=1l, message="You cannot get a product with 0 quantity. Please provide one")
    private Long quantity;
    
    public RequestInfo(
    		 Long productId,
    		 Long quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
    
    
    
}
