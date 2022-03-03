package com.poc.microservices.product.service;

import java.util.List;
import com.poc.microservices.product.entity.Product;
import com.poc.microservices.product.exceptions.NoDataFoundException;

public interface ProductService {

	Product saveProduct(Product product);
	Product updateProduct(Long id, Product request) throws NoDataFoundException;
	List<Product> getAllProduct();
}
