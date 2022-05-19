package com.product.controller;

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
import com.product.domain.Product;
import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
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

    ProductServiceImpl service;
    ModelMapper mapper= new ModelMapper();

    @Autowired
    public ProductController(ProductServiceImpl service) {
        this.service = service;
    }
    
    private String getUserName(HttpServletRequest req) {
		return new String(
				Base64.getDecoder().decode(req.getHeader("Authorization").substring(6))).split("[:]")[0];
	}
    

	@Operation(summary = "Add new Product",
			description = "Adds new product. Returns the added product with an HttpStatus of OK (200)")
	@ApiResponse(responseCode = "200", description = "OK : Product Successfully Created")
	@ApiResponse(responseCode = "400", description = "BAD_REQUEST : Provided Price for a product should not be less than or equal to zero")
	@ApiResponse(responseCode = "409", description = "CONFLICT : Either the requested code or name is conflicting with the existing ones on the database.")
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public Product save(@RequestBody @Valid ProductRequest request, BindingResult validationResult) throws JsonProcessingException{

		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				throw new ProductException(error.getDefaultMessage());
			});
		}

		//Get the product requested
		Product product = mapper
				.typeMap(ProductRequest.class, Product.class)
				.addMappings(mapping -> {
					mapping.map(ProductRequest::getPrice, Product::setPrice);
					mapping.map(ProductRequest::getStocks, Product::setStocks);})
				.map(request);

		
		return service.saveProduct(product);
	}


	@Operation(summary = "List Products",
			description = "Returns the list of available products if the consumer is under customers-group."
					+ "If the consumer is under admins-group, it will return all of the product details.")
	@ApiResponse(responseCode = "200", description = "OK: Successfully Retrieved")
	@GetMapping(value = "get-all", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getAllProducts(HttpServletRequest req) {
		
		if(getUserName(req).equalsIgnoreCase("admin"))
			return service.getAllProducts();
		
		return service.getAllProducts().stream()
				.map(product -> 
					mapper.typeMap(Product.class, ProductResponse.class)
					.addMapping(Product::getAvailable, ProductResponse::setStocks)
					.map(product)
				)
				.collect(Collectors.toList());
	}


	@Operation(summary = "Get one Product",
			description = "Retrieves the product with a product-id being specified on the path variable."
					+ "Returns all the product details of that certain product if the admin is the consumer."
					+ "Once the consumer is a customer, it will return filtered details ignoring some fields that should not be served to the customers.")
	@ApiResponse(responseCode = "200", description = "OK: Successfully Retrieved")
	@GetMapping(value = "{productId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getProduct(@PathVariable("productId") Long id, HttpServletRequest req) throws JsonProcessingException {
		if(getUserName(req).equalsIgnoreCase("admin"))
			return service.getProduct(id);
		
		return mapper.map(service.getProduct(id), ProductResponse.class);
	}


	@Operation(summary = "Update Product",
			description = "Updates a product with a product-id being specified on the path variable and the new updated details of the product."
					+ "Returns the updated product.")
	@ApiResponse(responseCode = "200", description = "OK : Successfully Updated")
	@ApiResponse(responseCode = "400", description = "BAD_REQUEST : Provided Price for a product should not be less than or equal to zero")
	@ApiResponse(responseCode = "409", description = "CONFLICT : Either the requested code or name is conflicting with the existing ones on the database.")
	@PutMapping(value="{productId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody()
	public Product updateProduct(@RequestBody @Valid ProductRequest updated, BindingResult validationResult, @PathVariable("productId") Long id) throws JsonProcessingException {

		if(validationResult.hasErrors()) {
			validationResult.getAllErrors().forEach(error->{
				service.throwException(error.getDefaultMessage());
			});
		}

		mapper.typeMap(ProductRequest.class, Product.class).addMappings(mapping -> {
			mapping.map(ProductRequest::getPrice, Product::setPrice);
			mapping.map(ProductRequest::getStocks, Product::setStocks);
		});
		
		return service.updateProduct(id, mapper.map(updated, Product.class));

	}
}
