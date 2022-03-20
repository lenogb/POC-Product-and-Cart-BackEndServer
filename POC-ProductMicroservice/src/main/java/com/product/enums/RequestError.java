package com.product.enums;

import lombok.Getter;

@Getter
public enum RequestError {
	//not found
	//conflict name
	//conflict code
	//out of stocks
	//exceed number of stocks

	NOTFOUND(">>> Product Not Found"),
	CONFLICTNAME(">>> Product name provided is existing already"),
	CONFLICTCODE(">>> Product code provided is existing already"),
	NOSTOCKS(">>> Item could not be processed due to out of stock"),
	QUANTITYEXCEEDED(">>> The quantity of the product exceeded the item stock supply"),
	NULLFIELDS(">>> Required fields"),
	UNACCEPTABLEINPUT(">>> Unacceptable Input"),
	ERROR("");
	
	private final String message;
	
	RequestError(String message){
		this.message=message;
	}
}
