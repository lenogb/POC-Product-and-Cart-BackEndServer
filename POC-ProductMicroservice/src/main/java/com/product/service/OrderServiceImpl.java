package com.product.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.domain.Cartitem;
import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.domain.Shippinginformation;
import com.product.dto.OrderRequest;
import com.product.enums.ShippingStatus;
import com.product.repository.OrderRepository;
import com.product.util.KeyValue;


@Service
public class OrderServiceImpl{

	@Autowired OrderRepository repo;
	@Autowired ProductServiceImpl productService;
	@Autowired String errorForInput;
	@Autowired ModelMapper mapper;

	
	public KeyValue createOrder(OrderRequest request) {
		
		Orderdetail orderdetails= new Orderdetail();
		Shippinginformation shipping = new Shippinginformation();
		Double total = 0d;
		List<Cartitem> allItems = repo.getAllItems(request.getCartId());
		for(Cartitem item: allItems) {
			Product product = productService.getProduct(item.getProductId());
			total+=(product.getPrice()*item.getQuantity());
		}
		shipping.setCourier(request.getShippingCourier());
		shipping.setTrackingNumber(randomize(10l));
		shipping.setStatus(ShippingStatus.TOSHIP);
		
		LocalDateTime now = LocalDateTime.now().plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		shipping.setEta(now.format(formatter));
		
		orderdetails.setShippinginfo(
				(Shippinginformation) repo.save(shipping));
		orderdetails.setCartId(request.getCartId());
		orderdetails.setTotal(total);
		repo.save(orderdetails);
		
		return new KeyValue(orderdetails,allItems);
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