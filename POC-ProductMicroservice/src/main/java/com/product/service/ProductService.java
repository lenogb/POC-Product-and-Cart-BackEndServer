package com.product.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.dto.order.OrderRequest;
import com.product.enums.InputViolation;
import com.product.enums.ServerStatus;
import com.product.exception.ProductException;
import com.product.repository.MicroserviceRepository;

public abstract class ProductService {
	
	@Autowired MicroserviceRepository repository;
	
	abstract Object save(Object product);
	abstract List<Product> getAllProducts();
	abstract Object getProduct(Long id);
	abstract Product updateProduct(Product updated);
	abstract Orderdetail createOrder(OrderRequest request, String consumer);

	private String errorForInput="Error found for input: ";
	
	
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
	
	
	public Integer countName(Product product) {
		return repository.count(product);
	}
	
	
	public Integer countCode(Product product) {
		return repository.count(product);
	}
	

	//CHECKING CONFLICTS IN REQUEST AGAINST THE DATABASE
	public void checkConflicts(Product product) {
		Integer nameCount = countName(product);
		Integer codeCount = countCode(product);
			
		if(nameCount>0) 
			throw new ProductException(HttpStatus.CONFLICT, ServerStatus.REQUEST_INVALID.name(),
				errorForInput+product.getName()+" under "+product.getCategory()+". "+InputViolation.CONFLICTNAME.getMessage());	
		if(codeCount>0) 
			throw new ProductException(HttpStatus.CONFLICT, ServerStatus.REQUEST_INVALID.name(),
				errorForInput+product.getCode()+". "+InputViolation.CONFLICTCODE);
	}

	
	
	//CHECK IF THE INPUTED PRICE IS GOOD
	public void checkPrice(Double price) {
		if(price<=0)
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				ServerStatus.REQUEST_INVALID.name(),
				errorForInput+"Price: "+price+": Invalid Price. "+InputViolation.UNACCEPTABLEINPUT.getMessage());
		
	}
		
		
	//CHECK IF OUT OF STOCK
	public void checkAvailability(Product product) {
		if(product.getAvailable()<=0) 
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				ServerStatus.REQUEST_INVALID.name(),
				errorForInput+"Product: "+product.getName()+" with available stock of -> "+product.getAvailable());
	}
		
	//CHECK IF THE QUANTITY OF THE GIVEN PRODUCT IS LESS THAN THE PRODUCT STOCK
	public void checkQuantity(Long qty, Product product) {
		if(qty<=0)
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				ServerStatus.REQUEST_INVALID.name(),
				errorForInput+"Product: "+product.getName()+". Quantity specified should not less than or equal to zero");
		if(qty>product.getAvailable())
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				ServerStatus.REQUEST_INVALID.name(),
				errorForInput+"Product: "+product.getName()+" with the quantity >>> "+qty+". "+InputViolation.QUANTITYEXCEEDED.getMessage());
	}
}
