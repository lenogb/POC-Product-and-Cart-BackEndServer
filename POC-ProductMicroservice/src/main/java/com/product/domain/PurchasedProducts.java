package com.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="purchasedhistory")
public class PurchasedProducts {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	private Long quantity;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="productId")
	private Product product;
	
	public PurchasedProducts(Long quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}

}
