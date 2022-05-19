package com.product.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductRequest {
	
	@NotEmpty(message="Category should not be empty. Required!")
	private String category;
	@NotEmpty(message="Name should not be empty. Required!")
	private String name;
	@NotEmpty(message="Code should not be empty. Required!")
	private String code;
	@NotEmpty(message="Description should not be empty. Required!")
	private String description;
	
	@Pattern(regexp = "[^a-z]*", message = "Price is invalid")
	@Digits(fraction = 2, integer = 5, message="Price is invalid")
	@Min(value=1,  message="Cannot have 0 price. Please provide one with minimum price of 1")
	@NotNull(message="Price should not be empty. Required!")
	private String price;
	
	@Pattern(regexp = "[^a-z]*", message = "Stocks is invalid")
	@Min(value=1, message="Cannot store a product with stock of 0. Please provide one with minimum number of 1")
	@NotNull(message="Stocks should not be empty. Required!")
	private String stocks;
	
	@Override
	public String toString() {
		return "ProductRequest [category=" + category + ", name=" + name + ", code=" + code + ", description="
				+ description + ", price=" + price + ", stocks=" + stocks + "]";
	}

	
}
