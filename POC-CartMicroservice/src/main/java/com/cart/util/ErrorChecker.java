package com.cart.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cart.enums.RequestError;
import com.cart.exception.NotFoundException;
import com.cart.exception.QuantityExceededException;
import com.cart.exception.UnacceptableInputException;
import com.cart.exception.UnavailableItemException;
import com.cart.mapper.CartMapper;
import com.cart.model.Product;

@Component
public class ErrorChecker{
	
	@Autowired CartMapper mapper;
	
	String errorForInput="Error found for input: ";
	
		//CHECK IF THE QUANTITY OF THE GIVEN PRODUCT IS LESS THAN THE PRODUCT STOCK
		public void checkQuantity(Long qty, Product product) {
			if(qty<=0)
				throw new UnacceptableInputException(
						RequestError.UNACCEPTABLEINPUT,
						errorForInput+"Product: "+product.getName()+". Quantity specified should not less than or equal to zero");
			if(qty>product.getStocks())
				throw new QuantityExceededException(
						RequestError.QUANTITYEXCEEDED, 
						errorForInput+"Product: "+product.getName()+" with the quantity >>> "+qty);
		}
		
		
		//CHECK IF PRODUCT IS EXISTING
		public void checkProductExistence(Product product) {
			if(product==null)
				throw new NotFoundException(
						RequestError.NOTFOUND,
						"Requesting for a Product that could not be Found");
		}
		
		
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
