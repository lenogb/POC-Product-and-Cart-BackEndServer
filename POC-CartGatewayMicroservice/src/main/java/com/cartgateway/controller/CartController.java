package com.cartgateway.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartgateway.dtos.RequestInfo;
import com.cartgateway.exception.ProcessFailedException;
import com.cartgateway.servercomm.grpc.CartMicroserviceCommunicator;
import com.cartgateway.servercomm.http.OrderRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cart Resource Controller", description = "Create Cart, Add Item, Remove Item, Update Item, Check-Out")
@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired CartMicroserviceCommunicator service;
	
	
	private String getUserName(HttpServletRequest req) {
		return new String(
				Base64.getDecoder().decode(req.getHeader("Authorization").substring(6))).split("[:]")[0];
	}

	
	@Operation(summary = "Add to Cart", description = "Adds new Item on the cart")
	@ApiResponse(responseCode = "200", description = "Item Successfully Added to Cart")
	@ApiResponse(responseCode = "400", description = "Request might be invalid or unavailable to resource")
	@PostMapping("add-to-cart")
    public Object addToCart(@RequestBody @Valid RequestInfo model, BindingResult validationResult, HttpServletRequest req) throws IOException, InterruptedException, ExecutionException  {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProcessFailedException(HttpStatus.BAD_REQUEST,error.getDefaultMessage());
			});
		}
		
		return service.addToCart(model, getUserName(req));
    }
    
	
	@Operation(summary = "Get all Items", description = "Gives all items inside the cart associated on the logged in account")
	@ApiResponse(responseCode = "200", description = "Success")
	@GetMapping
    public Object listAll(HttpServletRequest req) {
    	return service.listAll(getUserName(req));
    }
    
	
	@Operation(summary = "Remove Item", description = "Removes Item from the Cart associated on the account logged in")
	@ApiResponse(responseCode = "200", description = "Removed successfully")
	@ApiResponse(responseCode = "400", description = "Request might be invalid or unavailable to resource")
    @DeleteMapping
    public Object removeItem(@RequestBody @Valid RequestInfo request,BindingResult validationResult, HttpServletRequest req) throws Throwable {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProcessFailedException(HttpStatus.BAD_REQUEST,error.getDefaultMessage());
			});
		}
		
		return service.removeItem(request, getUserName(req));
    }
    
	@Operation(summary = "Place Order", description = "Process Orders")
	@ApiResponse(responseCode = "200", description = "Successfully Processed")
	@DeleteMapping(value="check-out")
    public Object checkOut(@RequestBody @Valid OrderRequest order, BindingResult validationResult, HttpServletRequest req) throws Throwable {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProcessFailedException(HttpStatus.BAD_REQUEST,error.getDefaultMessage());
			});
		}
    	
    	service.checkOut(getUserName(req), order);
    	
    	return "SUCCESSFULLY PROCESSED";
    }
	
}