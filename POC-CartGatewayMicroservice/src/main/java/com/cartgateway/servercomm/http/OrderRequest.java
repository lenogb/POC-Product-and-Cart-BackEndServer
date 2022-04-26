package com.cartgateway.servercomm.http;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.cartgateway.dtos.RequestInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Valid
public class OrderRequest {

	private List<RequestInfo> itemDetails; //Map<productId, Quantity>
	
	@NotEmpty(message="Empty Field: Courier. Required")
	private String shippingCourier;
	
	private String consumer;
	
	public OrderRequest(
			 List<RequestInfo> itemDetails, 
			 String shippingCourier,
			 String consumer) {
		super();
		this.itemDetails = itemDetails;
		this.shippingCourier = shippingCourier;
		this.consumer=consumer;
	}
	
}
