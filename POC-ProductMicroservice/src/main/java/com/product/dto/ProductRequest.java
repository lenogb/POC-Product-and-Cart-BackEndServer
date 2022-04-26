package com.product.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductRequest {
	
	@NotEmpty(message="Empty Input ---- Field: Category")
	private String category;
	@NotEmpty(message="Empty Input ---- Field: Name")
	private String name;
	@NotEmpty(message="Empty Input ---- Field: Code")
	private String code;
	@NotEmpty(message="Empty Input ---- Field: Description")
	private String description;
	@Min(value=1, message="Cannot have 0 price. Please provide one with minimum price of 1")
	@Max(value=50000, message="Price should not be greater than 50,000")
	private Double price;
	@Min(value=1, message="Cannot store a product with stock of 0. Please provide one with minimum number of 1")
	private Long stocks;
	
	@Override
	public String toString() {
		return "ProductRequest [category=" + category + ", name=" + name + ", code=" + code + ", description="
				+ description + ", price=" + price + ", stocks=" + stocks + "]";
	}
	
}
