package com.product.domain;

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
@Entity(name="items")
public class Item {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
	private Long productId;	//productID
	private Long quantity;
	private Double subTotal;
	private Long orderId;
	
	public Item(Long productId, Long quantity, Double subTotal, Long orderId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.orderId = orderId;
	}
}
