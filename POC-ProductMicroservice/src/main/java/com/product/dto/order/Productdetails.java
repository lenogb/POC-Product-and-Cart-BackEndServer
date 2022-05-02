package com.product.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productdetails {

	 @JsonProperty("Product_Id") private Long productId;
	 @JsonProperty("Name") private String name;
	 @JsonProperty("Price") private Double price;
}
