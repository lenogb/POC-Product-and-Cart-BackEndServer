package com.product.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="orderId")
	private Orderdetail order;
	
	private Long quantity;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="productId")
	private Product product;
	
	public PurchasedHistory(Orderdetail order, Long quantity, Product product) {
		this.order=order;
		this.quantity = quantity;
		this.product = product;
	}

}
