package com.product.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.product.domain.Product;
import com.product.dto.ProductRequest;
import com.product.enums.InputViolation;
import com.product.enums.ServerStatus;
import com.product.exception.ProductException;
import com.product.service.ProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Product Resource Controller")
@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired ProductServiceImpl service;
	@Autowired ModelMapper mapper;


	ObjectMapper jsonmapper = new ObjectMapper();
	FilterProvider filters = new SimpleFilterProvider()
		      .addFilter("productFilter", 
		    		  SimpleBeanPropertyFilter
				      .serializeAll());
	
	@Operation(summary = "Creating Product", description = "Saves product")
	@ApiResponse(responseCode = "200", description = "Product Successfully Created")
	@PostMapping(produces="application/json")
	public Object save(@RequestBody @Valid ProductRequest request,BindingResult validationResult) throws JsonProcessingException{
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProductException(HttpStatus.BAD_REQUEST,ServerStatus.REQUEST_INVALID.name(),error.getDefaultMessage());
			});
		}
		
		//Get the product requested
		var prequest = mapper.map(request, Product.class);
		
		//Check for conflicts
		service.checkConflicts(prequest);
		service.checkPrice(request.getPrice());
		
		prequest.setAvailable(prequest.getStocks());
		prequest.setBooked(0l);
		prequest.setCheckedout(0l);
		
		return jsonmapper.writer(filters)
			    		.writeValueAsString(mapper.map(service.save(prequest), com.product.dto.ProductResponse.class));
	}
	
	
	@Operation(summary = "Get all Products", description = "Gets all the lists of product")
	@ApiResponse(responseCode = "200", description = "Successfully Retrieved")
	@GetMapping(value = "get-all", produces="application/json") 
	public Object getAllProducts() throws JsonProcessingException {
		return jsonmapper.writer(filters).writeValueAsString(
				service.getAllProducts().stream().map(post -> 
		mapper.map(post, com.product.dto.ProductResponse.class)).collect(Collectors.toList()));
	}
	
	
	@Operation(summary = "Get one Product", description = "Retrieve the product with a product-id that will be specified on the path variable from Database")
	@ApiResponse(responseCode = "200", description = "Successfully Retrieved")
	@GetMapping(value = "{productId}", produces="application/json") 
	public Object getProduct(@PathVariable("productId") Long id) throws JsonProcessingException {
	    	    
	    return jsonmapper.writer(new SimpleFilterProvider()
	  	      .addFilter("productFilter", 
		    		  SimpleBeanPropertyFilter
		    	      .serializeAllExcept("productId")))
	    		.writeValueAsString(mapper.map(service.getProduct(id), com.product.dto.ProductResponse.class));
	}
	
	
	@Operation(summary = "Update Product", description = "Update a product with a product-id being specified on the path variable")
	@ApiResponse(responseCode = "200", description = "Successfully Updated")
	@PutMapping(value="{productId}", produces="application/json")
	@ResponseBody()
	public Object updateProduct(@RequestBody @Valid ProductRequest updated, BindingResult validationResult, @PathVariable("productId") Long id) throws JsonProcessingException {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProductException(HttpStatus.BAD_REQUEST,ServerStatus.REQUEST_INVALID.name(),error.getDefaultMessage());
			});
		}
		
		
		//GETTING THE PRODUCT
		var originalProduct = (Product) service.getProduct(id);
		var updatedProduct = mapper.map(updated, Product.class);
		
		//CHECKING ERRORS
		service.checkPrice(updated.getPrice());
		
		updatedProduct.setProductId(id);
		updatedProduct.setBooked(originalProduct.getBooked());
		updatedProduct.setCheckedout(originalProduct.getBooked());
		updatedProduct.setCreateDate(originalProduct.getCreateDate());
		updatedProduct.setModifiedDate(originalProduct.getModifiedDate());
		updatedProduct.setAvailable(updatedProduct.getStocks()-originalProduct.getBooked());
			 
		//ELSE CHANGES DETECTED
		if(!originalProduct.equals(updatedProduct))  {
			
			//VALIDATING NAME AND CODE IF THERE ARE ANY CONFLICTS
			Boolean nameChanged = Boolean.FALSE.equals(originalProduct.getName().equals(updatedProduct.getName()));
			Boolean codeChanged = Boolean.FALSE.equals(originalProduct.getCode().equals(updatedProduct.getCode()));
			
			if(Boolean.TRUE.equals(nameChanged))
				service.checkConflicts(
					new Product(updatedProduct.getCategory(), updatedProduct.getName(), null));
			if(Boolean.TRUE.equals(codeChanged))
				service.checkConflicts(
						new Product(updatedProduct.getCategory(), null , updatedProduct.getCode()));
			
			//VALIDATING NEW STOCKS
			if(updatedProduct.getStocks()<originalProduct.getBooked())
				throw new ProductException(
						HttpStatus.BAD_REQUEST,
						ServerStatus.REQUEST_INVALID.name(),
						"Invalid Stock Quantity Request Input: Refer to the below Information:\n"
						+ "*Reserved Quantity of Products: "+originalProduct.getBooked()+"\n"
						+ "The requested quantity of new stock is less than the reserved products which should not be affected"
						+InputViolation.UNACCEPTABLEINPUT.getMessage());
			
			
			return jsonmapper.writer(filters)
		    		.writeValueAsString(mapper.map(service.updateProduct(updatedProduct), com.product.dto.ProductResponse.class));
		}
		
		return jsonmapper.writer(filters)
				.writeValueAsString(mapper.map(service.updateProduct(originalProduct), com.product.dto.ProductResponse.class));
	}
}
