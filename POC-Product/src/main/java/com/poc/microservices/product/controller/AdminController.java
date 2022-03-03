package com.poc.microservices.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.microservices.product.dto.Request;
import com.poc.microservices.product.dto.Response;
import com.poc.microservices.product.entity.Product;
import com.poc.microservices.product.exceptions.AlreadyExistingException;
import com.poc.microservices.product.exceptions.EmptyFieldException;
import com.poc.microservices.product.exceptions.NoDataFoundException;
import com.poc.microservices.product.service.ServiceImpl;
import com.poc.microservices.product.util.Validator;
import com.poc.microservices.product.util.objectModel;

@RestController
public class AdminController {

	@Autowired ServiceImpl service;
	@Autowired ModelMapper modelMapper;
	@Autowired objectModel otherModel;
	@Autowired Validator validator;
	
	@PostMapping
	@ResponseBody
	public Response save(@RequestBody @Valid Request request, BindingResult result) throws Throwable{
		if(result.hasErrors()) {
			List<ObjectError> errors= result.getAllErrors();
			otherModel.setErrors(errors);
			throw new EmptyFieldException(otherModel.errorsToString());
		}
		if(!validator.productIsValid("", request.getName())) {
			throw new AlreadyExistingException(request.getName()+" is already existing.");
		}
		else {
			Product productRequest = modelMapper.map(request, Product.class);
			Product savedProduct = service.saveProduct(productRequest);
			Response productResponse = modelMapper.map(savedProduct, Response.class);
			return productResponse;
		}
	}
	
	@PutMapping("{id}")
	@ResponseBody
	public Response update(@PathVariable Long id, @RequestBody @Valid Request request, BindingResult result) throws NoDataFoundException {
		Product productRequest = modelMapper.map(request, Product.class);
		Product updatedProduct = service.updateProduct(id, productRequest);
		Response productResponse = modelMapper.map(updatedProduct, Response.class);
		return productResponse;
	}
}
