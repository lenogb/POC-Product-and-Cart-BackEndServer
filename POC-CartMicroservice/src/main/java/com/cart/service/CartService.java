package com.cart.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cart.enums.RequestError;
import com.cart.exception.CartException;
import com.cart.mapper.CartMapper;
import com.cart.model.Product;
import com.cart.model.RequestModel;


public abstract class CartService {

	@Autowired String errorForInput;
	@Autowired CartMapper mapper;
	
	abstract Object addToCart(RequestModel request);
	abstract Object listAllItems(Long cartId);
	abstract Object deleteItem(Long cartId, Long productId);
	abstract Object updateItemOnTheCart(RequestModel request);
	abstract Object checkOut(Long cartId);
	
	
	
	//CHECK IF THE GIVEN STRING IS PARSEABLE TO DIGIT
		public void checkIfParseable(String str) {
			try {
				Double.parseDouble(str);
			}
			catch(Exception e){
				throw new CartException(
						RequestError.UNACCEPTABLEINPUT,
						errorForInput+str+" and is not convertable to digit. Field is for digit input.");
			}
		}
		
		
		//CHECK IF THE QUANTITY OF THE GIVEN PRODUCT IS LESS THAN THE PRODUCT STOCK
		public void checkQuantity(Long qty, Product product) {
			if(qty<=0)
				throw new CartException(
						RequestError.UNACCEPTABLEINPUT, 
						errorForInput+"Product: "+product.getName()+
						". Quantity specified should not less than or equal to zero");
			if(qty>product.getStocks())
				throw new CartException(
						RequestError.QUANTITYEXCEEDED, 
						errorForInput+"Product: "+product.getName()+" with the quantity >>> "+qty);
		}
		
		
		//CHECK IF PRODUCT IS EXISTING
		public void checkProductExistence(Product product) {
			if(product==null)
				throw new CartException(
						RequestError.NOTFOUND,
						"You are requesting for a product that could not be found."
				);
		}
		
		
		//CHECK IF OUT OF STOCK
		public void checkStocks(Product product) {
			if(product.getStocks()<=0) 
				throw new CartException(
						RequestError.NOSTOCKS, 
						errorForInput+"Product: "+product.getName()+" with stocks of -> "+product.getStocks());
		}
		
		//CHECK IF ANY OPERATION TOWARD THE DATABASE IS SUCCESS
		public void checkIfOperationIsSuccess(int noOfSuccessOperation) {
			if(noOfSuccessOperation<=0)
				throw new CartException(
						RequestError.OPERATIONFAILED, 
						"");
		}
		
		//CHECK IF THE CART INSIDE THE CART TABLE ON THE DATABASE IS EXISTING
		public void checkCartExistence(Long cartId) {
			if(mapper.checkCartExistence(cartId)<=0)
				throw new CartException(
						RequestError.NOCARTFOUND,
						errorForInput+"Cart Id: "+cartId
						);
		}	
		
		//CHECK IF THE PRODUCT IS UNDER THE SPECIFIED CART
		public void checkProductUnderCart(Long cartId, Long productId) {
			if(mapper.checkProductExistence(cartId, productId)<=0) {
				throw new CartException(
						RequestError.NOTFOUND,
						errorForInput+"Product "+productId
						);
			}
		}
		
	
}
