package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.domain.Product;

@Service
public class ProductServiceImpl extends ProductService {
	
	@Override
	public Product save(Product product){
		return repository.save(product);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return repository.getAllProducts();
	}
	
	@Override
	public Product getProduct(long id) {
		checkProduct(id);
		return repository.getProduct(id);
	}
	
	@Override
	public Product updateProduct(Product updated,long id) {
		repository.updateProduct(updated, id);
		updated.setProductId(id);
		return updated;
	}
}
