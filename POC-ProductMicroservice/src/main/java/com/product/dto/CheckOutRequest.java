package com.product.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckOutRequest {

	private Long cartId;
	private List<ProductQuantity> products;
	private String courier;
	
}
