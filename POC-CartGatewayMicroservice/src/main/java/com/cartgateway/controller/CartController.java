package com.cartgateway.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartgateway.dtos.ResponseInfo;
import com.cartgateway.exception.ProcessFailedException;
import com.cartgateway.servercomm.grpc.CartMicroserviceCommunicator;
import com.cartgateway.servercomm.http.OrderRequest;
import com.cartgateway.servercomm.http.ProductMicroserviceCommunicator;
import com.cartgateway.servercomm.http.RequestInfo;


@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired CartMicroserviceCommunicator service;
	@Autowired ProductMicroserviceCommunicator pm;

	String message = "Call for Product Microservice method failed. Status: ";
	
	@PostMapping("add-to-cart")
    public Object addToCart(@RequestBody RequestInfo model, HttpServletRequest req) throws IOException, InterruptedException, ExecutionException  {
		
		service.addToCart(model, req.getUserPrincipal().getName());
		int statusOfProcess = pm.sendRequestToProductMicroservice("/book",model);
		
		if(statusOfProcess<=226) 
			return service.listAll(req.getUserPrincipal().getName());
		
		throw new ProcessFailedException(
				HttpStatus.valueOf(statusOfProcess),
				message+HttpStatus.valueOf(statusOfProcess)+" ["+HttpStatus.valueOf(statusOfProcess).getReasonPhrase()+"]");
    }
    
	@GetMapping
    public Object listAll(HttpServletRequest req) {
    	return service.listAll(req.getUserPrincipal().getName());
    }
    
    @DeleteMapping
    public Object removeItem(@RequestBody RequestInfo request, HttpServletRequest req) throws Throwable {
		service.removeItem(request, req.getUserPrincipal().getName());
		int statusOfProcess = pm.sendRequestToProductMicroservice("/remove", request);
		
		if(statusOfProcess<=202) 
			return service.listAll(req.getUserPrincipal().getName());
		
		throw new ProcessFailedException(
				HttpStatus.valueOf(statusOfProcess),
				message+HttpStatus.valueOf(statusOfProcess)+" ["+HttpStatus.valueOf(statusOfProcess).getReasonPhrase()+"]");
		
    }
    
   
	@DeleteMapping(value="check-out")
    public Object checkOut(@RequestBody OrderRequest order, HttpServletRequest req) throws Throwable {
		
		List<ResponseInfo> lists= service.listAll(req.getUserPrincipal().getName());
		List<RequestInfo> mapped = new ArrayList<>();
		lists.forEach(item ->{
			mapped.add(new RequestInfo(item.getProductId(), item.getQuantity()));
		});
		order.setItemDetails(mapped);
		order.setConsumer(req.getUserPrincipal().getName());
    	service.checkOut(req.getUserPrincipal().getName());
    	
    	int statusOfProcess = pm.sendRequestToProductMicroservice("/order", order);
    	
    	if(statusOfProcess<=202)
    		return "SUCCESSFULLY PROCESSED";
    	
    	throw new ProcessFailedException(
    			HttpStatus.valueOf(statusOfProcess),
    			message+HttpStatus.valueOf(statusOfProcess)+" ["+HttpStatus.valueOf(statusOfProcess).getReasonPhrase()+"]");
    }
	
}
