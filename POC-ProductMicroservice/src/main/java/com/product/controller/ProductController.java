package com.product.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.product.exception.ProductException;
import com.product.service.ProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Product Resource Controller", 
	description="This controller is the center of product management, it contains (POST) adding and (PUT) updating product that will only be consumed by an admin consumer.")
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
	
	@Operation(summary = "Add new Product", 
			description = "Adds new product. Returns the added product with an HttpStatus of OK (200)")
	@ApiResponse(responseCode = "200", description = "OK : Product Successfully Created")
	@ApiResponse(responseCode = "400", description = "BAD_REQUEST : Provided Price for a product should not be less than or equal to zero")
	@ApiResponse(responseCode = "409", description = "CONFLICT : Either the requested code or name is conflicting with the existing ones on the database.")
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public Object save(@RequestBody @Valid ProductRequest request,BindingResult validationResult) throws JsonProcessingException{
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProductException(error.getDefaultMessage());
			});
		}
		
		mapper.typeMap(ProductRequest.class, Product.class).addMappings(mapping -> {
			mapping.map(ProductRequest::getPrice, Product::setPrice);
			mapping.map(ProductRequest::getStocks, Product::setStocks);
			});
		
		//Get the product requested
		Product prequest = mapper.map(request, Product.class);
		prequest.setPriceAndStocks(Double.valueOf(request.getPrice())
				,Long.valueOf(request.getStocks()));
		
		//Check for conflicts
		service.checkConflicts(prequest);
		
		prequest.setAvailable(prequest.getStocks());
		prequest.setBooked(0l);
		prequest.setCheckedout(0l);
		
		return jsonmapper.writer(filters)
			    		.writeValueAsString(mapper.map(service.save(prequest), com.product.dto.ProductResponse.class));
	}
	
	
	@Operation(summary = "List Products", 
			description = "Returns the list of available products if the consumer is under customers-group."
					+ "If the consumer is under admins-group, it will return all of the product details.")
	@ApiResponse(responseCode = "200", description = "OK: Successfully Retrieved")
	@GetMapping(value = "get-all", produces=MediaType.APPLICATION_JSON_VALUE) 
	public Object getAllProducts(HttpServletRequest req) throws JsonProcessingException {
		String consumer = new String(
				Base64.getDecoder().decode(req.getHeader("Authorization").substring(6))).split("[:]")[0];
		
		if(consumer.equalsIgnoreCase("admin"))
			return service.getAllProducts();
		
		
		return jsonmapper.writer(filters).writeValueAsString(
				service.getAllProducts().stream().map(product -> 
				mapper.map(product, com.product.dto.ProductResponse.class)).collect(Collectors.toCollection(ArrayList::new)));
	}
	
	
	@Operation(summary = "Get one Product", 
			description = "Retrieves the product with a product-id being specified on the path variable."
					+ "Returns all the product details of that certain product if the admin is the consumer."
					+ "Once the consumer is a customer, it will return filtered details ignoring some fields that should not be served to the customers.")
	@ApiResponse(responseCode = "200", description = "OK: Successfully Retrieved")
	@GetMapping(value = "{productId}", produces=MediaType.APPLICATION_JSON_VALUE) 
	public Object getProduct(@PathVariable("productId") Long id, HttpServletRequest req) throws JsonProcessingException {
		
		String consumer = new String(
				Base64.getDecoder().decode(req.getHeader("Authorization").substring(6))).split("[:]")[0];
		
		if(consumer.equalsIgnoreCase("admin"))
			return service.getProduct(id);
			
	    return jsonmapper.writer(new SimpleFilterProvider()
	  	      .addFilter("productFilter", 
		    		  SimpleBeanPropertyFilter
		    	      .serializeAllExcept("productId")))
	    		.writeValueAsString(mapper.map(service.getProduct(id), com.product.dto.ProductResponse.class));
	}
	
	
	@Operation(summary = "Update Product", 
			description = "Updates a product with a product-id being specified on the path variable and the new updated details of the product."
					+ "Returns the updated product.")
	@ApiResponse(responseCode = "200", description = "OK : Successfully Updated")
	@ApiResponse(responseCode = "400", description = "BAD_REQUEST : Provided Price for a product should not be less than or equal to zero")
	@ApiResponse(responseCode = "409", description = "CONFLICT : Either the requested code or name is conflicting with the existing ones on the database.")
	@PutMapping(value="{productId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody()
	public Object updateProduct(@RequestBody @Valid ProductRequest updated, BindingResult validationResult, @PathVariable("productId") Long id) throws JsonProcessingException {
		
		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				service.throwException(error.getDefaultMessage());
			});
		}
		
		mapper.typeMap(ProductRequest.class, Product.class).addMappings(mapping -> {
			mapping.map(ProductRequest::getPrice, Product::setPrice);
			mapping.map(ProductRequest::getStocks, Product::setStocks);
			});
		
		
		//GETTING THE PRODUCT
		var originalProduct = (Product) service.getProduct(id);
		var updatedProduct = mapper.map(updated, Product.class);
		
		updatedProduct.setPriceAndStocks(Double.valueOf(updated.getPrice())
				,Long.valueOf(updated.getStocks()));
		
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
						"Invalid Stock Quantity Request Input: Refer to the below Information:\n"
						+ "*Reserved Quantity of Products: "+originalProduct.getBooked()+"\n"
						+ "The requested quantity of new stock is less than the reserved products which should not be affected");
			
			
			return jsonmapper.writer(filters)
		    		.writeValueAsString(mapper.map(service.updateProduct(updatedProduct), com.product.dto.ProductResponse.class));
		}
		
		return jsonmapper.writer(filters)
				.writeValueAsString(mapper.map(service.updateProduct(originalProduct), com.product.dto.ProductResponse.class));
	}
}
