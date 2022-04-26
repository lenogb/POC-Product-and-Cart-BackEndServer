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
	private Long orderId;
	private Long quantity;
	private Long productId;
	private Date orderTime;
	
	public void setQuantityAndProduct(Long orderId, Long quantity, Long productId) {
		this.orderId=orderId;
		this.quantity = quantity;
		this.productId = productId;
	}

}
