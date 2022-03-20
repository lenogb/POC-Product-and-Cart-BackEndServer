package com.product.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderRequest {

	private List<ItemRequest> items;
	private ShippingInfosDTO shippingInfo;
}
