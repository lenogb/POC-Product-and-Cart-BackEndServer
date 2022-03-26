package com.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.dto.CheckOutRequest;
import com.product.dto.CheckOutResponse;
import com.product.dto.ItemResponse;
import com.product.dto.ProductQuantity;
import com.product.dto.ShippingInfo;
import com.product.service.OrderServiceImpl;
import com.product.service.ProductServiceImpl;


@RestController
@RequestMapping("api/v1/check-out")
public class OrdersController {

	@Autowired OrderServiceImpl service;
	@Autowired ModelMapper mapper;
	@Autowired ProductServiceImpl prodService;
	
	@PostMapping	
	public ResponseEntity<Object> creatingOrder(@RequestBody CheckOutRequest request) {
		
		Orderdetail createdOrder = service.createOrder(request);
		CheckOutResponse response = mapper.map(createdOrder, CheckOutResponse.class);
		List<ItemResponse> items = new ArrayList<>();
		
		for(ProductQuantity item : request.getProducts()) {
			Product product = prodService.getProduct(item.getProductId());
			ItemResponse prodResponse = new ItemResponse(
					product.getCode(),
					product.getName(),
					product.getPrice(),
					item.getQuantity(),
					item.getSubTotal()
					);
			items.add(prodResponse);
		}
			
			response.setItems(items);
			response.setShippinginfo(mapper.map(createdOrder.getShippinginfo(), ShippingInfo.class));
			return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
