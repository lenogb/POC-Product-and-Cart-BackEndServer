package com.product.enums;

import lombok.Getter;

@Getter
public enum Errors {
	//not found
	//conflict name
	//conflict code
	//out of stocks
	//exceed number of stocks

	NOTFOUND(" | Requesting for a product that could not be found"),
	NOSTOCKS(" | Requesting for a product that is Out of Stock"),
	QUANTITYEXCEEDED(" | The quantity specified exceeded the product's stock supply"),
	NULLFIELDS(" | Field should not be empty. Highly required."),
	UNACCEPTABLEINPUT(" | Unprocessable input"),
	ERROR(""),
	CONFLICTNAME(" | Product name provided is conflicting with the same product name under the specified category."),
	CONFLICTCODE(" | Product code provided should be unique. Same product code is already on the database.");
	
	private final String message;
	
	Errors(String message){
		this.message=message;
	}
}
