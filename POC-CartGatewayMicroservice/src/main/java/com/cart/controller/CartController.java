package com.cart.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.dtos.Order;
import com.cart.dtos.RequestModel;
import com.cart.dtos.Response;
import com.cartservice.grpc.CartServiceGrpc.CartServiceBlockingStub;
import com.cartservice.grpc.ListOfItems;


@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired ModelMapper modelMapper;
    @Autowired CartServiceBlockingStub stub;
    
	@PostMapping
    public ResponseEntity<List<Response>> addToCart(@RequestBody RequestModel request) {
    	
    	//Map the request to the compiled class
		ListOfItems response = stub.addToCart(mapRequest(request));
    	return new ResponseEntity<>(mapList(response), HttpStatus.OK);
    }
    
	
    @GetMapping("{id}")
    public ResponseEntity<Object> listAll(@PathVariable("id") Long cartId) {
    	ListOfItems response = stub.listAllItems(com.cartservice.grpc.Request.newBuilder()
    			.setCartId(cartId)
    			.build());
    	return new ResponseEntity<>(mapList(response), HttpStatus.OK);
    }
    

    @DeleteMapping("{id}")
    public ResponseEntity<Object> removeItem(@PathVariable("id") Long cartId, @RequestBody RequestModel request) {
    	request.setCartId(cartId);
    	request.setQuantity(0l);
    	ListOfItems response = stub.deleteItem(mapRequest(request));
    	return new ResponseEntity<>(mapList(response), HttpStatus.OK);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Object> updateItemOnTheCart(@PathVariable("id") Long cartId, @RequestBody RequestModel request) {
    	request.setCartId(cartId);
    	ListOfItems response = stub.updateItemOnTheCart(mapRequest(request));
    	return new ResponseEntity<>(mapList(response), HttpStatus.OK);
    }
    
	@DeleteMapping("check-out")
    public ResponseEntity<Object> checkOut(@RequestBody Order request) {
		com.cartservice.grpc.Order response = stub.checkOut(
				com.cartservice.grpc.Order.newBuilder()
				.setCartId(request.getCartId())
				.setCourier(request.getCourier())
				.build());
		
		List<Response> list = new ArrayList<>();
		response.getItemsList().forEach(item->{
			list.add(new Response(item.getProductId(),item.getQuantity(), item.getSubTotal()));
		});
		
    	return new ResponseEntity<>(new Order(
				response.getCartId(), 
				list, response.getCourier()), HttpStatus.OK);
    }
    
    
    public List<Response> mapList(ListOfItems object){
    	List<Response> list = new ArrayList<>();
    	for(int i=0; i<object.getItemsCount(); i++) {
    		list.add(modelMapper.map(object.getItems(i), Response.class));
    	}
    	return list;
    }
    
    public com.cartservice.grpc.Request mapRequest(RequestModel request){
    	return com.cartservice.grpc.Request.newBuilder()
    			.setCartId(request.getCartId())
    			.setProductId(request.getProductId())
    			.setQuantity(request.getQuantity())
    			.build();
    }
    

}
