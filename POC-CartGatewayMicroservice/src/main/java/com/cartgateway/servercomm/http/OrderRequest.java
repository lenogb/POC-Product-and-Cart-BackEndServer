package com.cartgateway.servercomm.http;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.cartgateway.dtos.RequestInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequest {

	private Map<Long, Long> itemDetails; //Map<productId, Quantity>
	
	@NotEmpty(message="Empty Field: Courier. Required")
	private String shippingCourier;
	
	private String consumer;
	
	public OrderRequest(
			 Map<Long, Long> itemDetails,
			 String shippingCourier,
			 String consumer) {
		super();
		this.itemDetails = itemDetails;
		this.shippingCourier = shippingCourier;
		this.consumer=consumer;
	}
	
}
