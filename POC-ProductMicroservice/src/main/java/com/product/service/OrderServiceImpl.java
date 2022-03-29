package com.product.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.domain.Orderdetail;
import com.product.domain.Shippinginformation;
import com.product.dto.CheckOutRequest;
import com.product.dto.ProductQuantity;
import com.product.enums.ShippingStatus;
import com.product.repository.OrderRepository;


@Service
public class OrderServiceImpl{

	@Autowired OrderRepository repo;
	@Autowired ProductServiceImpl productService;
	@Autowired String errorForInput;
	@Autowired ModelMapper mapper;

	
	public Orderdetail createOrder(CheckOutRequest request) {
		
		Orderdetail orderdetails= new Orderdetail();
		Shippinginformation shipping = new Shippinginformation();
		
		Double total = 0d;
		for(ProductQuantity product : request.getProducts()) {
			total=+product.getSubTotal();
		}
		
		
		shipping.setCourier(request.getCourier());
		shipping.setTrackingNumber(randomize(10l));
		shipping.setStatus(ShippingStatus.TOSHIP);
		
		LocalDateTime now = LocalDateTime.now().plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		shipping.setEta(now.format(formatter));
		
		orderdetails.setShippinginfo(
				(Shippinginformation) repo.save(shipping));
		orderdetails.setCartId(request.getCartId());
		orderdetails.setTotal(total);
		
		return (Orderdetail) repo.save(orderdetails);
	}

	
	private Long randomize(Long size) {
		StringBuilder sbuilder= new StringBuilder();
		for(int i=0; i<size; i++) {
			sbuilder.append('9');
		}
		
	    Long result = ThreadLocalRandom.current().nextLong(Long.parseLong(sbuilder.toString()));
	    if(repo.countTrackingNumber(result)>0 || (sbuilder.length()!=size)) 
	    	randomize(size);
		return result;
	}
}