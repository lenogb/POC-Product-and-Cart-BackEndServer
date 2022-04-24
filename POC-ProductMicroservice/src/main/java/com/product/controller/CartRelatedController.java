package com.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.domain.PurchasedHistory;
import com.product.order.dto.OrderRequest;
import com.product.order.dto.RequestInfo;
import com.product.service.ProductServiceImpl;


@RestController
@RequestMapping("cartRelatedOperations")
public class CartRelatedController {

	@Autowired ModelMapper mapper;
	@Autowired ProductServiceImpl service;
	
	Gson gson = new GsonBuilder().create();

	@PutMapping("order")
	public void createOrder(@RequestBody String fromCartGatewayRequest) throws Throwable  {
		
		OrderRequest order = gson.fromJson(fromCartGatewayRequest, OrderRequest.class);
		Orderdetail createdOrder = service.createOrder(order, order.getConsumer());
		
		order.getItemDetails().forEach(product->{
			var purchased = mapper.map(createdOrder, PurchasedHistory.class);
			purchased.setQuantityAndProduct(product.getQuantity(), product.getProductId());
			service.save(purchased);
		});
		
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
	
	
	@GetMapping("getOrder/{shippingstatus}")
	public void getOrders(@PathVariable("shippingstatus") String sStatus, HttpServletRequest res) {
		//by shipping status
		//by consumer --> String customer = res.getUserPrincipal().getName();
		
	}
	
	


	//Booking of Product (customer is adding to cart)
	@PutMapping("book")
	public void bookProduct(@RequestBody String fromCartGatewayRequest) throws Throwable{
		
		RequestInfo request = gson.fromJson(fromCartGatewayRequest, RequestInfo.class);
		Long pid = request.getProductId();
		Long quantity = request.getQuantity();
		
		Product product = service.getProduct(pid);
		
		product.setBooked(product.getBooked()+quantity);
		product.setAvailable(product.getStocks()-product.getBooked());
		
		service.updateProduct(product);
	}
		
		
	@PutMapping("remove")
	public void afterRemovingItemFromCart(@RequestBody String fromCartGatewayRequest) throws Throwable{
		
		RequestInfo request = gson.fromJson(fromCartGatewayRequest, RequestInfo.class);
		
		Long pid = request.getProductId();
		Long quantity = request.getQuantity();
		
		Product product = service.getProduct(pid);
		
		product.setBooked(product.getBooked()-quantity);
		product.setAvailable(product.getStocks()-product.getBooked());
		
		service.updateProduct(product);
	}
	
}
