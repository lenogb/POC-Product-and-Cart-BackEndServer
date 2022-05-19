package com.product.service;

import com.product.domain.Product;
import com.product.exception.ProductException;
import com.product.repository.MicroserviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

public abstract class ProductService {

	@Autowired MicroserviceRepository repository;

	protected String generateTracking(int size) {
		StringBuilder stringBuilder= new StringBuilder();
		for(int i=0; i<size; i++) {
			stringBuilder.append('9');
		}
		Long result = ThreadLocalRandom.current().nextLong(Long.parseLong(stringBuilder.toString()));
	    //Generated tracking containing the Custom prefix and the randomized number on its specified size
	    String generated = "POC" + result.toString();
	    if(repository.countTrackingNumber(generated)>0 || generated.length()!=(size+3)) {
	    		generateTracking(size);
	    }

		return generated;
	}


	//CHECKING CONFLICTS IN REQUEST AGAINST THE DATABASE
	public void checkConflicts(Product product) {
        Long nameCount = repository.getAllProducts().stream().filter(p -> p.getName().equals(product.getName())).count();
        Long codeCount = repository.getAllProducts().stream().filter(p -> p.getCode().equals(product.getCode())).count();
		if(nameCount>0)
			throwException("Product name is conflicting with the existing name under the same Category. Please product unique one");
		if(codeCount>0)
			throwException("The product code "+product.getCode()+" is already existing. It should be unique.");
	}


    Product temp;


	//CHECK IF THE QUANTITY OF THE GIVEN PRODUCT IS LESS THAN THE PRODUCT STOCK
	public void checkQuantity(Long qty, Long pid) {
        temp = repository.getProductById(pid);
		if(qty<=0)
			throwException("The quantity of a product should not less than or equal to zero");
		if(qty>temp.getAvailable())
			throwException("Quantity should not be greater than the number of product's availability. Available: "+temp.getAvailable());
	}

	public void throwException(String message) {
		throw new ProductException(message);
	}
}
