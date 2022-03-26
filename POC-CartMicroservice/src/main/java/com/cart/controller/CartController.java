package com.cart.controller;

import java.util.List;

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

import com.cart.dto.CheckOut;
import com.cart.dto.ProductQuantity;
import com.cart.dto.RequestModel;
import com.cart.service.CartServiceImpl;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {


    @Autowired CartServiceImpl service;
    
	
    @PostMapping("{id}")
    public ResponseEntity<Object> addToCart(@PathVariable("id") Long cartId, @RequestBody RequestModel request) {
    	request.setCartId(cartId);
    	return new ResponseEntity<>(service.addToCart(request), HttpStatus.OK);
    }
    
	
    @GetMapping("{id}")
    public ResponseEntity<Object> listAll(@PathVariable("id") Long cartId) {
    	return new ResponseEntity<>(service.listAllItems(cartId), HttpStatus.OK);
    }
    

    @DeleteMapping("{id}")
    public ResponseEntity<Object> removeItem(@PathVariable("id") Long cartId, @RequestBody RequestModel request) {
    	request.setCartId(cartId);
    	return new ResponseEntity<>(service.deleteItem(request.getCartId(), request.getProductId()), HttpStatus.OK);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Object> updateItemOnTheCart(@PathVariable("id") Long cartId, @RequestBody RequestModel request) {
    	request.setCartId(cartId);
    	return new ResponseEntity<>(service.updateItemOnTheCart(request), HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@DeleteMapping("check-out")
    public ResponseEntity<Object> checkOut(@RequestBody CheckOut request) {
    	Object products = service.checkOut(request.getCartId());
    	request.setProducts((List<ProductQuantity>) products);
    	return new ResponseEntity<>(request, HttpStatus.OK);
    }
    

}
