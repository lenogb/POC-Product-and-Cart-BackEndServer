package com.cart.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckOut {

	private Long cartId;
	private List<ProductQuantity> products;
	private String courier;
	
	
}
