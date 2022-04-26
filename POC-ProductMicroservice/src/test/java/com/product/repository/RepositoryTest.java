package com.product.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.product.domain.Product;

class RepositoryTest {
	
	
	
	@Autowired MicroserviceRepository repo;
//	
//	@DisplayName("Test if get all product doesn't throw")
//	@Test
//	void testgetAllProducts() {
//		Product product = new Product();
//        Product savedProduct = repo.save(product);
//        assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);
//	}
	

}
