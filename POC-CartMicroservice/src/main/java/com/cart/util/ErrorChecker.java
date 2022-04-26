package com.cart.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cart.enums.RequestError;
import com.cart.exception.NotFoundException;
import com.cart.exception.UnavailableItemException;
import com.cart.mapper.CartMapper;
import com.cart.model.Product;

@Component
public class ErrorChecker{
	
	@Autowired CartMapper mapper;
	
	String errorForInput="Error found for input: ";
	
		
		//CHECK IF OUT OF STOCK
		public void checkAvailability(Product product) {
			if(product.getAvailable()<=0) 
				throw new UnavailableItemException(
					RequestError.UNAVAILABLE,
					errorForInput+"Product: "+product.getName()+" with available stock of -> "+product.getAvailable());
		}
		
		
		//CHECK IF THE PRODUCT IS UNDER THE SPECIFIED CART
		public void checkProductUnderCart(Long cartId, Long productId) {
			if(mapper.checkProductExistence(cartId, productId)<=0) 
				throw new NotFoundException(
						RequestError.NOTFOUND,
						errorForInput+"Product "+productId+" | Product specified could not be found under the cart specified"
						);
		}
		
}
