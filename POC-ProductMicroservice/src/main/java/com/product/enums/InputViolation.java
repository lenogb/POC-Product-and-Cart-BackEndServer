package com.product.enums;

import lombok.Getter;

@Getter
public enum InputViolation {

	NOTFOUND("| Requesting for a product that could not be found"),
	UNAVAILABLE(" | Requesting for a product that is Out of Stock or Unavailable"),
	QUANTITYEXCEEDED(" | The quantity specified exceeded the product's stock supply"),
	NULLFIELDS(" | Field should not be empty. Highly required."),
	UNACCEPTABLEINPUT(" | Unprocessable input"),
	DATASOURCERROR(""),
	CONFLICTNAME(" | Product name provided is conflicting with the same product name under the specified category."),
	CONFLICTCODE(" | Product code provided should be unique. Same product code is already on the database.");
	
	private final String message;
	
	InputViolation(String message){
		this.message=message;
	}
}
