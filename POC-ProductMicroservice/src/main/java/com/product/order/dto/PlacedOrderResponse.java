package com.product.order.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlacedOrderResponse {

	private Long orderId;
//    private List<ResponseInfo> items;
    private Double total;
    private Date orderTime;
    private String trackingNumber;
    private String courier;
    private String eta;
    private String status;
    
    public PlacedOrderResponse(
    		@JsonProperty("Order ID") Long orderId, 
//    		@JsonProperty("Items") List<ResponseInfo> items, 
    		@JsonProperty("Total") Double total, 
    		@JsonProperty("Date of Order") Date orderTime,
    		@JsonProperty("Tracking-no") String trackingNumber, 
			@JsonProperty("Shipping Courier") String courier, 
			@JsonProperty("Estimated Time of Arrival") String eta, 
			@JsonProperty("Shipping-status") String status) {
		super();
		this.orderId = orderId;
//		this.items = items;
		this.total = total;
		this.orderTime = orderTime;
		this.trackingNumber = trackingNumber;
		this.courier = courier;
		this.eta = eta;
		this.status = status;
		}
}
