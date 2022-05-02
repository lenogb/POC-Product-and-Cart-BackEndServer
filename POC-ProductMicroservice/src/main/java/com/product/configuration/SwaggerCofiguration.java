package com.product.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerCofiguration {

	 @Bean
	 public OpenAPI openApi() {
	        return new OpenAPI()
	           .info(new Info()
	             .title("POC- Product Microservice")
	             .description("Spring boot microservice exposing rest endpoints for Products (listing ,addition of\r\n"
							+ "products & booking of products). Accepts orders. Broadcast all the activities to Kafka for the logger Microservice to log"
							+ "Database Used is MySQL. Hibernate is the ORM framework used to interact with MySQL."
							)
	             .version("v1.0")
	             .contact(new Contact()
	               .name("Lenie Mae Gonzales")
	               .email("lenocon24@gmail.com"))
	        );
	   }
	
}
