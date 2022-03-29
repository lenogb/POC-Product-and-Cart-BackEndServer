package com.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cart.enums.RequestError;
import com.cart.exception.CartException;
import com.cart.mapper.CartMapper;
import com.cart.model.Product;
import com.cartservice.grpc.Item;
import com.cartservice.grpc.ListOfItems;
import com.cartservice.grpc.Request;

import io.grpc.stub.StreamObserver;

@Component
public class Actions{
	
	@Autowired String errorForInput;
	@Autowired CartMapper mapper;
	
	public void getList(
			Request request, StreamObserver<ListOfItems> responseObserver){
		
		List<Item> lists = new ArrayList<>();
		mapper.findAll(request.getCartId()).forEach(cartitem->{
			lists.add(Item.newBuilder()
					.setProductId(cartitem.getProductId())
					.setQuantity(cartitem.getQuantity())
					.setSubTotal(cartitem.getSubTotal())
					.build());
		});

		responseObserver.onNext(ListOfItems.newBuilder()
				.addAllItems(lists)
				.build());
        responseObserver.onCompleted();
		
	}
	
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
						errorForInput+"Product "+productId+" | Product specified could not be found under the cart specified"
						);
			}
		}
		
	
}
