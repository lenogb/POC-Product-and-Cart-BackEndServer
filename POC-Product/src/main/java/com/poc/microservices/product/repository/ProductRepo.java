package com.poc.microservices.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.microservices.product.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	Boolean existsByName(String name);
}
