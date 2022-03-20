package com.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.product.domain.Product;
import com.product.enums.RequestError;
import com.product.exception.ProductException;
import com.product.model.HttpstatusModel;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImpl {
	
	@Autowired private ProductRepository repository;
	@Autowired HttpstatusModel validationResult;
	@Autowired String errorForInput;
	
	public void setUserReposatory(ProductRepository userReposatory) {
		this.repository = userReposatory;
	}

	public Product save(Product product){
		 return repository.save(product);
	}
	
	public List<Product> getAllProducts() {
		return repository.getAllProducts();
	}
	
	public Product getProduct(long id) {
		return repository.getProduct(id);
	}
	
	public Product updateProduct(Product updated,long id) {
		repository.updateProduct(updated, id);
		return updated;
	}
	
	public Product deleteProduct(long id) {
		return repository.deleteProduct(id);
	}
	
	public Integer countName(String name, String category) {
		return repository.count(new Product(category, name, null));
	}
	
	public Integer countCode(String code) {
		return repository.count(new Product(null, null, code));
	}
	
	
	//CHECKING CONFLICTS IN REQUEST AGAINST THE DATABASE
	public void checkConflicts(Product product) {
		Integer nameCount =countName(product.getName(),product.getCategory());
		Integer codeCount =countCode(product.getCode());
		
		if(nameCount>0) 
			throw new ProductException(HttpStatus.CONFLICT, RequestError.CONFLICTNAME,
					errorForInput+product.getName()+" under "+product.getCategory());	
		if(codeCount>0) 
			throw new ProductException(HttpStatus.CONFLICT, RequestError.CONFLICTCODE,
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
					RequestError.NULLFIELDS,
					Arrays.toString(errorMessages.toArray()));
		}
	}
	
	
	//CHECKING IF PRODUCT IS NULL
	public void checkProduct(Long id) {
		Product product = getProduct(id);
		if(product==null)
			throw new ProductException(
					HttpStatus.NOT_FOUND, 
					RequestError.NOTFOUND,
					errorForInput+id.toString());
	}
	
	
	public void check(String str) {
		try {
			Double.parseDouble(str);
		}
		catch(Exception e){
			throw new ProductException(
					HttpStatus.BAD_REQUEST,
					RequestError.UNACCEPTABLEINPUT,
					errorForInput+str+" and is not convertable to digit. Field is for digit input.");
		}
	}
}
