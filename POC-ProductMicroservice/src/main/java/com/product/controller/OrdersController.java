package com.product.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.domain.Item;
import com.product.domain.Product;
import com.product.domain.ShippingInformation;
import com.product.dto.ItemResponse;
import com.product.dto.OrderRequest;
import com.product.dto.OrderResponse;
import com.product.dto.ShippingInfosDTO;
import com.product.service.OrderServiceImpl;
import com.product.service.ProductServiceImpl;
import com.product.util.KeyValue;


@RestController
@RequestMapping("version1/check-out")
public class OrdersController {

	@Autowired OrderServiceImpl service;
	@Autowired ModelMapper mapper;
	@Autowired ProductServiceImpl prodService;
	
	@PostMapping("{customerId}")
	public ResponseEntity<Object> creatingOrder(@PathVariable Long customerId,@RequestBody OrderRequest request) {
		
			KeyValue createdOrder = service.createOrder(
					customerId, 
					request.getItems().stream().map(post -> 
						mapper.map(post, Item.class)).collect(Collectors.toList()), 
					mapper.map(request.getShippingInfo(), ShippingInformation.class));
			
			OrderResponse response = mapper.map(createdOrder.getKey(), OrderResponse.class);
			ArrayList<ItemResponse> items = new ArrayList<>();
			createdOrder.getValue().forEach(item->{
				ItemResponse mapped= mapper.map(item, ItemResponse.class);
				Product product = prodService.getProduct(item.getProductId());
				mapped.setProductCode(product.getCode());
				mapped.setProductName(product.getName());
				mapped.setProductPrice(product.getPrice());
				items.add(mapped);
			});
			response.setItems(items);
			response.setShippinginfo(mapper.map(createdOrder.getKey().getShippinginfo(), ShippingInfosDTO.class));
			return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
