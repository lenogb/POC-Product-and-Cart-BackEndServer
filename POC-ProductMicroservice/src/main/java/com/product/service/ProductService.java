package com.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.dto.ExceptionStatusModel;
import com.product.enums.InputViolation;
import com.product.exception.ProductException;
import com.product.order.dto.OrderRequest;
import com.product.repository.MicroserviceRepository;

public abstract class ProductService {
	
	@Autowired MicroserviceRepository repository;
	@Autowired ExceptionStatusModel validationResult;
	
	abstract Object save(Object product);
	abstract List<Product> getAllProducts();
	abstract Product getProduct(Long id);
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
			throw new ProductException(HttpStatus.CONFLICT, InputViolation.CONFLICTNAME,
				errorForInput+product.getName()+" under "+product.getCategory());	
		if(codeCount>0) 
			throw new ProductException(HttpStatus.CONFLICT, InputViolation.CONFLICTCODE,
				errorForInput+product.getCode());
	}
		
		
		
	//CHECKING NULLS IN REQUESTS
	public void checkNulls(BindingResult result) {
		if(result.hasErrors()) {
			List<String> errorMessages = new ArrayList<>();
			result.getAllErrors().forEach((e) -> {
				errorMessages.add(e.getDefaultMessage());
			});
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				InputViolation.NULLFIELDS,
				Arrays.toString(errorMessages.toArray()));
		}
	}
		
		
	//CHECKING IF PRODUCT IS NULL
	public void checkProduct(Long id) {
		Product product = repository.getProduct(id);
		if(product==null)
			throw new ProductException(
				HttpStatus.NOT_FOUND, 
				InputViolation.NOTFOUND,
				errorForInput+id);
	}
	
	
	//CHECK IF THE INPUTED PRICE IS GOOD
	public void checkPrice(Double price) {
		if(price<=0)
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				InputViolation.UNACCEPTABLEINPUT,
				errorForInput+"Price: "+price+": Invalid Price");
		
	}
		
		
	//CHECK IF OUT OF STOCK
	public void checkAvailability(Product product) {
		if(product.getAvailable()<=0) 
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				InputViolation.UNAVAILABLE,
				errorForInput+"Product: "+product.getName()+" with available stock of -> "+product.getAvailable());
	}
		
	//CHECK IF THE QUANTITY OF THE GIVEN PRODUCT IS LESS THAN THE PRODUCT STOCK
	public void checkQuantity(Long qty, Product product) {
		if(qty<=0)
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				InputViolation.UNACCEPTABLEINPUT,
				errorForInput+"Product: "+product.getName()+". Quantity specified should not less than or equal to zero");
		if(qty>product.getAvailable())
			throw new ProductException(
				HttpStatus.BAD_REQUEST,
				InputViolation.QUANTITYEXCEEDED, 
				errorForInput+"Product: "+product.getName()+" with the quantity >>> "+qty);
	}
}
