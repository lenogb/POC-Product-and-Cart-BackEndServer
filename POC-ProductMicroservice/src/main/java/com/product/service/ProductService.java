package com.product.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;

import com.product.domain.Product;
import com.product.enums.Check;
import com.product.exception.ProductException;
import com.product.repository.MicroserviceRepository;

public abstract class ProductService {
	
	@Autowired MicroserviceRepository repository;

	
	protected String generateTracking(int size) {
		StringBuilder sbuilder= new StringBuilder();
		for(int i=0; i<size; i++) {
			sbuilder.append('9');
		}
		Long result = ThreadLocalRandom.current().nextLong(Long.parseLong(sbuilder.toString()));
	    //Generated tracking containing the Custom prefix and the randomized number on its specified size
	    String generated = "POC" + result.toString();
	    if(repository.countTrackingNumber(generated)>0 || generated.length()!=(size+3)) {
	    		generateTracking(size);
	    }
	    
		return generated;
	}
	

	//CHECKING CONFLICTS IN REQUEST AGAINST THE DATABASE
	public void checkConflicts(Product product) {
		if(repository.count(product, Check.NAME)>0) 
			throwException("Product name is conflicting with the existing name under the same Category. Please product unique one");
		if(repository.count(product, Check.CODE)>0) 
			throwException("The product code "+product.getCode()+" is already existing. It should be unique.");
	}

	
		
		
	//CHECK IF OUT OF STOCK
	public void checkAvailability(Product product) {
		if(product.getAvailable()<=0) 
			throwException("The product specified is no longer available");
	}
		
	//CHECK IF THE QUANTITY OF THE GIVEN PRODUCT IS LESS THAN THE PRODUCT STOCK
	public void checkQuantity(Long qty, Product product) {
		if(qty<=0)
			throwException("The quantity of a product should not less than or equal to zero");
		if(qty>product.getAvailable())
			throwException("Quantity should not be greater than the number of product's availability. Available: "+product.getAvailable());
	}
	
	
	public void throwException(String message) {
		throw new ProductException(message);
	}
}
