package com.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.domain.Product;
import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.HttpstatusModel;
import com.product.service.ProductServiceImpl;

@RestController
@RequestMapping("version1/product")
public class ProductController {

	@Autowired ProductServiceImpl service;
	@Autowired ModelMapper mapper;
	@Autowired HttpstatusModel status;
	@Autowired String errorForInput;
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid ProductRequest request, BindingResult result){
		service.checkNulls(result);
		service.check(request.getPrice());
		service.check(request.getStocks());
		Product productRequest = mapper.map(request, Product.class);
		service.checkConflicts(productRequest);
		return new ResponseEntity<>(
			mapper.map(service.save(productRequest), ProductResponse.class), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Object>> getAllProducts() {
		return new ResponseEntity<>(service.getAllProducts().stream().map(post -> 
		mapper.map(post, ProductResponse.class)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getProduct(@PathVariable(value="id")Long id) {
		service.checkProduct(id);
		return new ResponseEntity<>(mapper.map(service.getProduct(id), ProductResponse.class),HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody @Valid ProductRequest updated, BindingResult result, @PathVariable(value="id")Long id) {
		
		//CHECKING ERRORS
		service.checkProduct(id);
		service.checkNulls(result);
		service.check(updated.getPrice());
		service.check(updated.getStocks());
		
		
		//GETTING THE PRODUCT
		Product originalProduct = service.getProduct(id);
		Product updatedProduct = mapper.map(updated, Product.class);
		
		//IF REQUESTED PRODUCT FOR PATCHING HAS NO CHANGES
		if(originalProduct.equals(updatedProduct)) {
			return new ResponseEntity<>(mapper.map(originalProduct, ProductResponse.class), HttpStatus.OK);
		}
		
		//ELSE CHANGES DETECTED
		else {
			
			Boolean nameIsUnchanged = Boolean.TRUE.equals(originalProduct.getName().equals(updatedProduct.getName()));
			Boolean codeIsUnchanged = Boolean.TRUE.equals(originalProduct.getCode().equals(updatedProduct.getCode()));
			Boolean categoryIsUnchanged = Boolean.TRUE.equals(originalProduct.getCategory().equals(updatedProduct.getCategory()));
			
			
			//IF ONLY NAME IS UNCHANGED
			if(nameIsUnchanged && !codeIsUnchanged) 
				service.checkConflicts(
						new Product(updatedProduct.getCategory(), null, updatedProduct.getCode()));
				
			//IF ONLY CODE IS UNCHANGED
			else if(!nameIsUnchanged && codeIsUnchanged) 
				service.checkConflicts(
						new Product(updatedProduct.getCategory(), updatedProduct.getName(), null));
			
			//IF BOTH NAME AND CODE IS UNCHANGED BUT CATEGORY HAS CHANGED
			else if(nameIsUnchanged && codeIsUnchanged && !categoryIsUnchanged)
				service.checkConflicts(new Product(originalProduct.getCategory(), updatedProduct.getName(), null));
				
			//IF BOTH NAME AND CODE IS CHANGED
			else service.checkConflicts(updatedProduct);
				
			//
			return new ResponseEntity<>(
					mapper.map(service.updateProduct(updatedProduct, id), ProductResponse.class), 
					status.getApistatus());
			
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id")long id) {
		service.checkProduct(id);
		return new ResponseEntity<>(service.deleteProduct(id), HttpStatus.OK);
	}
	
}
