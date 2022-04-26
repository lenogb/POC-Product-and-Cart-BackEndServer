package com.cartgateway.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

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
import com.cartgateway.dtos.ResponseInfo;
import com.cartgateway.exception.ProcessFailedException;
import com.cartgateway.servercomm.grpc.CartMicroserviceCommunicator;
import com.cartgateway.servercomm.http.OrderRequest;
import com.cartgateway.servercomm.http.ProductMicroserviceCommunicator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cart Resource Controller", description = "Create Cart, Add Item, Remove Item, Update Item, Check-Out")
@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired CartMicroserviceCommunicator service;
	@Autowired ProductMicroserviceCommunicator pm;

	
	@Operation(summary = "Add to Cart", description = "Adds new Item on the cart")
	@ApiResponse(responseCode = "200", description = "Item Successfully Added to Cart")
	@ApiResponse(responseCode = "400", description = "Request might be invalid or unavailable to resource")
	@PostMapping("add-to-cart")
    public Object addToCart(@RequestBody RequestInfo model, BindingResult validationResult, HttpServletRequest req) throws IOException, InterruptedException, ExecutionException  {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProcessFailedException(HttpStatus.BAD_REQUEST,error.getDefaultMessage());
			});
		}
		List<ResponseInfo> resultLists = service.addToCart(model, req.getUserPrincipal().getName());
		if(!resultLists.isEmpty()) 
			pm.sendRequestToProductMicroservice("/book",model);
		
		return resultLists;
    }
    
	
	@Operation(summary = "Get all Items", description = "Gives all items inside the cart associated on the logged in account")
	@ApiResponse(responseCode = "200", description = "Success")
	@GetMapping
    public Object listAll(HttpServletRequest req) {
    	return service.listAll(req.getUserPrincipal().getName());
    }
    
	
	@Operation(summary = "Remove Item", description = "Removes Item from the Cart associated on the account logged in")
	@ApiResponse(responseCode = "200", description = "Removed successfully")
	@ApiResponse(responseCode = "400", description = "Request might be invalid or unavailable to resource")
    @DeleteMapping
    public Object removeItem(@RequestBody RequestInfo request,BindingResult validationResult, HttpServletRequest req) throws Throwable {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProcessFailedException(HttpStatus.BAD_REQUEST,error.getDefaultMessage());
			});
		}
		
		pm.sendRequestToProductMicroservice("/remove", request);
		
		return service.removeItem(request, req.getUserPrincipal().getName());
    }
    
	@Operation(summary = "Place Order", description = "Process Orders")
	@ApiResponse(responseCode = "200", description = "Successfully Processed")
	@DeleteMapping(value="check-out")
    public Object checkOut(@RequestBody OrderRequest order, BindingResult validationResult, HttpServletRequest req) throws Throwable {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProcessFailedException(HttpStatus.BAD_REQUEST,error.getDefaultMessage());
			});
		}
		
		List<ResponseInfo> lists= service.listAll(req.getUserPrincipal().getName());
		List<RequestInfo> mapped = new ArrayList<>();
		lists.forEach(item ->{
			mapped.add(new RequestInfo(item.getProductId(), item.getQuantity()));
		});
		order.setItemDetails(mapped);
		order.setConsumer(req.getUserPrincipal().getName());
    	
    	
    	pm.sendRequestToProductMicroservice("/order", order);
    	
    	service.checkOut(req.getUserPrincipal().getName());
    	return "SUCCESSFULLY PROCESSED";
    }
	
}
