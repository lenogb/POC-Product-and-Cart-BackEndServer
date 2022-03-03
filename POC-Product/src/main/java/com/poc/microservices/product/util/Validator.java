package com.poc.microservices.product.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.microservices.product.service.ServiceImpl;

@Component
public class Validator {

	@Autowired ServiceImpl service;
	
	public Boolean productIsValid(String oldName, String newName){
		while(oldName.equals(newName)) {
			return true;
		}
		return !service.checkProductName(newName); 
	}
}
