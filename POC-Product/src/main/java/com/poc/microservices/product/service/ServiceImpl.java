package com.poc.microservices.product.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.microservices.product.entity.Product;
import com.poc.microservices.product.exceptions.NoDataFoundException;
import com.poc.microservices.product.repository.ProductRepo;

@Service
@Transactional
public class ServiceImpl implements ProductService{

	@Autowired(required=false) ProductRepo repo;
	
	@Override
	public Product saveProduct(Product product) {
		return repo.save(product);
	}
	
	@Override
	public Product updateProduct(Long id, Product request) throws NoDataFoundException {
			Product product= repo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("Product not Found!"));
			product.setName(request.getName());
			product.setDescription(request.getDescription());
			return product;
	}
	
	@Override
	public List<Product> getAllProduct(){
		return repo.findAll();
	}
	
	public Boolean checkProductName(String name) {
		if(repo.existsByName(name))
				return true;
		else return false;
	}
}
