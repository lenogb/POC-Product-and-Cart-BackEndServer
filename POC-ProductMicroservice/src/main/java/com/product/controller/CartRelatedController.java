package com.product.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.domain.PurchasedHistory;
import com.product.dto.order.OrderRequest;
import com.product.dto.order.RequestInfo;
import com.product.logging.LogSender;
import com.product.service.ProductServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Cart-related oprations Resource Controller", description = "This Controller receives request from Cart-Gateway Microservice via Http Communication."
		+ "Because, there are some operations that the product microservice database is affected whenever request from the client is toward the Cart-gateway."
		)
@RestController
@RequestMapping("cartRelatedOperations")
public class CartRelatedController {

	
	@Autowired ProductServiceImpl service;
	@Autowired ModelMapper mapper;
	@Autowired LogSender logger;

	Gson gson = new GsonBuilder().create();

	@PutMapping("order")
	public ResponseEntity<HttpStatus> createOrder(@RequestBody String fromCartGatewayRequest) throws Throwable  {
		
		OrderRequest order = gson.fromJson(fromCartGatewayRequest, OrderRequest.class);
		Orderdetail createdOrder = service.createOrder(order, order.getConsumer());
		
		order.getItemDetails().forEach(product->{
			var purchased = mapper.map(createdOrder, PurchasedHistory.class);
			purchased.setQuantityAndProduct(createdOrder.getOrderId(), product.getQuantity(), product.getProductId());
			service.save(purchased);
		});
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		//RETURN THE ORDER RESPONSE
//		var placedOrder = mapper.map(createdOrder, PlacedOrderResponse.class);
//		
//		List<ResponseInfo> items = new ArrayList<>();
//		for(RequestInfo purchased : order.getItemDetails()) {
//			var productInfo = mapper.map(service.getProduct(purchased.getProductId()), ResponseInfo.class);
//			productInfo.setQuantity(purchased.getQuantity());
//			items.add(productInfo);
//		}
//		placedOrder.setItems(items);
//		placedOrder.setCourier(createdOrder.getShippinginfo().getCourier());
//		placedOrder.setTrackingNumber(createdOrder.getShippinginfo().getTrackingNumber());
//		placedOrder.setEta(createdOrder.getShippinginfo().getEta());
//		placedOrder.setStatus(createdOrder.getShippinginfo().getStatus().toString());
	}
	
	
	
	//Booking of Product (customer is adding to cart)
	@PutMapping("book")
	public ResponseEntity<HttpStatus> bookProduct(@RequestBody String fromCartGatewayRequest) throws Throwable{
		logger.logThis("Request received from Cart-Gateway Microservice: "+fromCartGatewayRequest);
		RequestInfo request = gson.fromJson(fromCartGatewayRequest, RequestInfo.class);
		
		Long pid = request.getProductId();
		Long quantity = request.getQuantity();
		
		Product product = (Product) service.getProduct(pid);
		
		service.checkQuantity(quantity, product);
		
		product.setBooked(product.getBooked()+quantity);
		product.setAvailable(product.getStocks()-product.getBooked());
		
		service.updateProduct(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
		
	@PutMapping("remove")
	public ResponseEntity<HttpStatus> afterRemovingItemFromCart(@RequestBody String fromCartGatewayRequest) throws Throwable{
		
		RequestInfo request = gson.fromJson(fromCartGatewayRequest, RequestInfo.class);
		
		Long pid = request.getProductId();
		Long quantity = request.getQuantity();
		
		Product product = (Product) service.getProduct(pid);
		
		product.setBooked(product.getBooked()-quantity);
		product.setAvailable(product.getStocks()-product.getBooked());
		
		service.updateProduct(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
