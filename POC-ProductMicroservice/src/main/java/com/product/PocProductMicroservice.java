package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class PocProductMicroservice extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PocProductMicroservice.class, args);
	}

}
