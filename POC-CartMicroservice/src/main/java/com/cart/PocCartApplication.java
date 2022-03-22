package com.cart;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cart.model.Cartitem;
import com.cart.model.Product;

@MappedTypes({Cartitem.class,Product.class})
@MapperScan("com.cart.mapper")
@SpringBootApplication
public class PocCartApplication {


	public static void main(String[] args) {
		SpringApplication.run(PocCartApplication.class, args);
	}
	
	
}
