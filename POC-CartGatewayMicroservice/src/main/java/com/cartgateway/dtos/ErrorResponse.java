package com.cartgateway.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

	private String debugmessage;
	private String status;
		

	public ErrorResponse(String debugmessage, String status) {
		this.debugmessage = debugmessage;
		this.status = status;
	}
	
	
}
