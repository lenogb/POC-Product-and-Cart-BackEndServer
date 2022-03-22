package com.product.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cartitem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
	
	@Column(updatable = false)
	private Long productId;	//unupdatable
	private Long quantity;	
	private Double subTotal;
	
	@Column(updatable = false)
	private Long cartId;	//unupdatable	
	
	
	public Cartitem(Long productId, Long quantity, Double subTotal, Long cartId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.cartId = cartId;
	}
}
