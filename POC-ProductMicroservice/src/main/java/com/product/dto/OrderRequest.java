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
public class OrderRequest {

	@NotNull(message="Empty Input ---- Field: Name")
	private Long cartId;
	@NotEmpty(message="Empty Input ---- Field: Name")
	private String shippingCourier;
}
