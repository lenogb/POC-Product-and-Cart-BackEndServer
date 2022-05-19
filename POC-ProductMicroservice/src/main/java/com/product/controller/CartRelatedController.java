package com.product.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.product.domain.Product;
import com.product.dto.order.RequestInfo;
import com.product.logging.LogSender;
import com.product.service.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Cart-related oprations Resource Controller", description = "This Controller receives request from Cart-Gateway Microservice via Http Communication."
		+ "Because, there are some operations that the product microservice database is affected whenever request from the client is toward the Cart-gateway."
)
@RestController
@RequestMapping("cartRelatedOperations")
public class CartRelatedController {

	ProductServiceImpl service;
    LogSender logger;

	@Autowired
    public CartRelatedController(ProductServiceImpl service, LogSender logger) {
        this.service = service;
        this.logger = logger;
    }

    Gson gson = new GsonBuilder().create();

	//Booking of Product (customer is adding to cart)
	@Operation(summary = "Book Product",
			description = "Once the consumer wanted to add some certain item/product to it's cart, this method will be called via HttpCommunication."
					+ "Booked and Available columns of the Product table on the POC database will be updated."
					+ "The number of Booked will be calculated based upon to how many is the quantity of a certain product the consumer wish to add to it's cart."
					+ "And the Number of Available will be based upon to the number of stocks not yet booked."
					+ "The request will be in the form of String object and a format of json. "
					+ "The object will then be mapped to a pojo class to use all it's values within the logic using Gson class."
					+ "Returns only httpStatus 200, once successfully processed to tell the client server that everything is working well.")
	@ApiResponse(responseCode = "200", description = "Ok : Successfully booked")
	@ApiResponse(responseCode = "400", description = "Bad-Request : "
			+ "The number of quantity was either less than or equal to zero or greater than the number of available stock of the product.")
	@PutMapping("book")
	public ResponseEntity<HttpStatus> bookProduct(@RequestBody String fromCartGatewayRequest) throws Throwable{
		logger.logThis("Request received from Cart-Gateway Microservice: "+fromCartGatewayRequest);
		RequestInfo request = gson.fromJson(fromCartGatewayRequest, RequestInfo.class);

		Long pid = request.getProductId();
		Long quantity = request.getQuantity();

		Product product = service.getProduct(pid);

		service.checkQuantity(quantity, pid);

		product.setBooked(product.getBooked()+quantity);

		service.updateProduct(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Update the product",
			description = "Once the consumer wanted to remove some certain item/product from it's cart, this method will be called via HttpCommunication."
					+ "And update the number of Booked and Available toward the Product table of the POC database."
					+ "The request will be in the form of String object and in a format of json. "
					+ "The object will then be mapped to a pojo class to use all it's values within the logic using Gson class."
					+ "Returns only httpStatus 200, once successfully processed to tell the client server that everything is working well.")
	@ApiResponse(responseCode = "200", description = "OK : Successfully processed")
	@PutMapping("remove")
	public ResponseEntity<HttpStatus> afterRemovingItemFromCart(@RequestBody String fromCartGatewayRequest) throws Throwable{
		logger.logThis("Request received from Cart-Gateway Microservice: "+fromCartGatewayRequest);
		RequestInfo request = gson.fromJson(fromCartGatewayRequest, RequestInfo.class);

		Product product = service.getProduct(request.getProductId());

		product.setBooked(product.getBooked()-request.getQuantity());

		service.updateProduct(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
