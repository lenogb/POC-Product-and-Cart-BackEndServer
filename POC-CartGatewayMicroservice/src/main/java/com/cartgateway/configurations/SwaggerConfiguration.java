package com.cartgateway.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

	@Bean
	 public OpenAPI openApi() {
	        return new OpenAPI()
	           .info(new Info()
	             .title("POC- Cart Gateway Microservice")
	             .description("Cart Gateway Microservice is the one that receives any requests from the Kong Gateway. "
	             		+ "It exposes rest endpoints for Cart related operations like Creating Cart, Updating Cart by adding and removing products or items from it, and get the cart by serving it's lists of items as a response."
	             		+ "All the corresponding methods will be invoked using gRPC calls toward the independent Cart Microservice that contains all the necessary methods implementing the rpc methods from rpc service being programmed."
						)
	             .version("v1.0")
	             .contact(new Contact()
	               .name("Lenie Mae Gonzales")
	               .email("lenocon24@gmail.com"))
	        );
	}
}
