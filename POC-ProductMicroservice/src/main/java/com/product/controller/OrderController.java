package com.product.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.domain.PurchasedHistory;
import com.product.dto.order.OrderRequest;
import com.product.logging.LogSender;
import com.product.service.ProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Order Resource Controller", description = "This Controller receives request from Cart-Gateway Microservice via Http Communication."
		+ "Request for order will be received by the Cart-Gateway Microservice to make the cart associated to the customer's account, as considered checked-out cart"
		+ ". All of the items will be removed from the cart and call this method via HttpCommunication to give the item's details of the cart and process the order under the specified consumer."
)
@RestController
@RequestMapping("processOrder")
public class OrderController {

	@Autowired ProductServiceImpl service;
	@Autowired LogSender logger;
	

	Gson gson = new GsonBuilder().create();

	@Operation(summary = "Creates Order", 
			description = "This method will process the order and store it's processed order details in the database."
					+ "The request will be in the form of String object and a format of json. "
					+ "The object will then be mapped to a pojo class to use all it's values within the logic using Gson class."
					+ "Returns only httpStatus 200, once successfully processed to tell the client server that everything is working well.")
	@ApiResponse(responseCode = "200", description = "OK")
	@PutMapping("order")
	public ResponseEntity<HttpStatus> createOrder(@RequestBody String fromCartGatewayRequest) throws Throwable  {
		logger.logThis("Request received from Cart-Gateway Microservice: "+fromCartGatewayRequest);
		OrderRequest order = gson.fromJson(fromCartGatewayRequest, OrderRequest.class);
		Orderdetail createdOrder = service.createOrder(order, order.getConsumer());
		
		order.getItemDetails().forEach(product->{
			Product productInfo = (Product) service.getProduct(product.getProductId());
			service.save(
					new PurchasedHistory(createdOrder, product.getQuantity(), productInfo)
			);
		});
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Object> getOrder(HttpServletRequest req) throws JsonSyntaxException, JsonProcessingException{
		String consumer = new String(
				Base64.getDecoder().decode(req.getHeader("Authorization").substring(6))).split("[:]")[0];
		
		return new ResponseEntity<>(service.getAllOrders(consumer),HttpStatus.OK);
	}
}
