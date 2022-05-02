package com.cart.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cartitem {

	@Id
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
