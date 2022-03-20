package com.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.domain.Item;
import com.product.domain.OrderDetail;
import com.product.domain.Product;
import com.product.domain.ShippingInformation;
import com.product.enums.RequestError;
import com.product.exception.ProductException;
import com.product.repository.OrderRepository;
import com.product.util.KeyValue;


@Service
public class OrderServiceImpl{

	@Autowired OrderRepository order;
	@Autowired ProductServiceImpl productService;
	@Autowired String errorForInput;

	public Item saveItem(Item item) {
		Product product = productService.getProduct(item.getProductId());
		if(product == null) 
			throw new ProductException(
					HttpStatus.NOT_FOUND, 
					RequestError.NOTFOUND, 
					errorForInput+item.getProductId().toString());
		item.setSubTotal(product.getPrice()*item.getQuantity());
		return (Item) order.save(item);
	}
	

	public KeyValue createOrder(Long customerId, List<Item> items, ShippingInformation shippingInformation) {
		OrderDetail orderdetails= new OrderDetail();
		Double total = 0d;
		for(Item item: items) {
			Product product = productService.getProduct(item.getProductId());
			if(product.getStocks()<=0) 
				throw new ProductException(
						HttpStatus.BAD_REQUEST, 
						RequestError.NOSTOCKS, 
						errorForInput+productService.getProduct(item.getProductId()).getName());
			if(item.getQuantity()>product.getStocks())
				throw new ProductException(
						HttpStatus.BAD_REQUEST, 
						RequestError.QUANTITYEXCEEDED, 
						errorForInput+productService.getProduct(item.getProductId()).getName());
			total+=(product.getPrice()*item.getQuantity());
		}
		
		shippingInformation.setTrackingNumber(randomize(10l));
		shippingInformation.setStatus("To ship");
		shippingInformation.setEta(LocalDateTime.now().plusDays(3));
		orderdetails.setShippinginfo((ShippingInformation) order.save(shippingInformation));
		orderdetails.setCustomerId(customerId);
		orderdetails.setTotal(total);
		order.save(orderdetails);
		
		items.forEach(item->{
			Product product = productService.getProduct(item.getProductId());
			if(product == null) 
				throw new ProductException(
						HttpStatus.NOT_FOUND, 
						RequestError.NOTFOUND, 
						errorForInput+item.getProductId().toString());
			item.setSubTotal(product.getPrice()*item.getQuantity());	//calculating the subtotal based on the quantity and price of the item
			product.setStocks(product.getStocks()-item.getQuantity());	//lessening the stocks by the item's quantity
			productService.updateProduct( product, product.getProductId());	//update the product
			
			order.save(new Item(	//save the item to the item's table
					item.getProductId(),
					item.getQuantity(), 
					product.getPrice()*item.getQuantity(), 
					orderdetails.getOrderId()));
			});
		
		return new KeyValue(orderdetails,items);
	}

	private Long randomize(Long size) {
		StringBuilder sbuilder= new StringBuilder();
		for(int i=0; i<size; i++) {
			sbuilder.append('9');
		}
		
	    Long result = ThreadLocalRandom.current().nextLong(Long.parseLong(sbuilder.toString()));
	    if(order.countTrackingNumber(result)>0 || (sbuilder.length()!=size)) 
	    	randomize(size);
		return result;
	}

}