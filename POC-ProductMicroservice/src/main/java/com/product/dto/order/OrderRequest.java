package com.product.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class OrderRequest {

	private Map<Long, Long> itemDetails; //Map<productId, Quantity>
	private String shippingCourier;
	private String consumer;
	
	public OrderRequest(
			Map<Long, Long> itemDetails,
			String shippingCourier,
			String consumer) {
		this.itemDetails = itemDetails;
		this.shippingCourier = shippingCourier;
		this.consumer=consumer;
	}
}
