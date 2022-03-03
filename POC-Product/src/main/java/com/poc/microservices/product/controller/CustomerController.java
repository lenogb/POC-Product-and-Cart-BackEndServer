package com.poc.microservices.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.microservices.product.dto.Response;
import com.poc.microservices.product.service.ServiceImpl;
import com.poc.microservices.product.util.Dto_Entity_Mapper;

@RestController
@RequestMapping("/user")
public class CustomerController {

	@Autowired ServiceImpl service;
	@Autowired KafkaTemplate<String, String> kafkaTemplate;
	@Autowired Dto_Entity_Mapper modelMapper;
	
	@GetMapping
	public List<Response> getAllProduct() {
		return service.getAllProduct().stream().map(post -> modelMapper.map(post, Response.class))
				.collect(Collectors.toList());
	}
	
//	@GetMapping("/addtocart/{id}")
//	
}
