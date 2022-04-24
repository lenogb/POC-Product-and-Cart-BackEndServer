package com.product.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotNull(message="Empty Input ---- Field: Price")
	private Double price;
	@NotNull(message="Empty Input ---- Field: Stock")
	private Long stocks;
	
	@Override
	public String toString() {
		return "ProductRequest [category=" + category + ", name=" + name + ", code=" + code + ", description="
				+ description + ", price=" + price + ", stocks=" + stocks + "]";
	}
	
}
