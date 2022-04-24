package com.product.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="purchasedhistory")
public class PurchasedHistory{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	private String consumer;
	private Long quantity;
	private Long productId;
	private Date orderTime;
	
	public void setQuantityAndProduct(Long quantity, Long productId) {
		this.quantity = quantity;
		this.productId = productId;
	}

}
