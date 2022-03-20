package com.product.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ShippingInfosDTO {

	private Long trackingNumber;
	private String courier;
	private String status; 
	private LocalDateTime eta; //Estimated Time of Arrival	//should be 3 days from the day of order creation
	
	@Override
	public String toString() {
		return "(courier=" + courier+")";
	}
}
