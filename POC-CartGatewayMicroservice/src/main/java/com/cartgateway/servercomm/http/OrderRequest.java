package com.cartgateway.servercomm.http;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequest {

	private List<RequestInfo> itemDetails; //Map<productId, Quantity>
	private String shippingCourier;
	private String consumer;
	
	public OrderRequest(
			@JsonProperty("Items") List<RequestInfo> itemDetails, 
			@JsonProperty("Shipping Courier") String shippingCourier,
			@JsonProperty("Consumer") String consumer) {
		super();
		this.itemDetails = itemDetails;
		this.shippingCourier = shippingCourier;
		this.consumer=consumer;
	}
	
}
