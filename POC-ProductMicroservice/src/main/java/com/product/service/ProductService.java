package com.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.product.domain.Product;
import com.product.dto.ExceptionStatusModel;
import com.product.enums.Errors;
import com.product.exception.ProductException;
import com.product.repository.ProductRepository;

public abstract class ProductService {
	
	@Autowired ProductRepository repository;
	@Autowired ExceptionStatusModel validationResult;
	@Autowired String errorForInput;
	
	//Methods to be implemented
	abstract Product save(Product product);
	abstract List<Product> getAllProducts();
	abstract Product getProduct(long id);
	abstract Product updateProduct(Product updated,long id);
	
	
	//
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
				throw new ProductException(HttpStatus.CONFLICT, Errors.CONFLICTNAME,
						errorForInput+product.getName()+" under "+product.getCategory());	
			if(codeCount>0) 
				throw new ProductException(HttpStatus.CONFLICT, Errors.CONFLICTCODE,
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
						Errors.NULLFIELDS,
						Arrays.toString(errorMessages.toArray()));
			}
		}
		
		
		//CHECKING IF PRODUCT IS NULL
		public void checkProduct(Long id) {
			Product product = repository.getProduct(id);
			if(product==null)
				throw new ProductException(
						HttpStatus.NOT_FOUND, 
						Errors.NOTFOUND,
						errorForInput+id.toString());
		}
		
		
		public void checkIfConvertable(String str) {
			try {
				Double.parseDouble(str);
			}
			catch(Exception e){
				throw new ProductException(
						HttpStatus.BAD_REQUEST,
						Errors.UNACCEPTABLEINPUT,
						errorForInput+str+" and is not convertable to digit. Field is for digit input.");
			}
		}
}
