package com.cart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cartitem {

	private Long itemId;
	private Long productId;	
	private Long quantity;	
	private Double subTotal;
	private Long cartId;	
	
	public Cartitem(Long productId, Long quantity, Double subTotal, Long cartId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.cartId = cartId;
	}
}
