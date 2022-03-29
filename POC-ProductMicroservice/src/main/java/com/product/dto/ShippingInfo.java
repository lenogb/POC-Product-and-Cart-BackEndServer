package com.product.dto;

import org.springframework.stereotype.Component;

import com.product.enums.ShippingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ShippingInfo {

	private Long trackingNumber;
	private String courier;
	private ShippingStatus status; 
	private String eta; //Estimated Time of Arrival	//should be 3 days from the day of order creation
	
	@Override
	public String toString() {
		return "(courier=" + courier+")";
	}
}
