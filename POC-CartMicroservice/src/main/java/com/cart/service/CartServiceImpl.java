package com.cart.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.cart.mapper.CartMapper;
import com.cart.model.Cartitem;
import com.cart.model.Product;
import com.cartservice.grpc.Item;
import com.cartservice.grpc.ListOfItems;
import com.cartservice.grpc.Order;
import com.cartservice.grpc.Request;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class CartServiceImpl extends com.cartservice.grpc.CartServiceGrpc.CartServiceImplBase{
	
	@Autowired CartMapper mapper;
	@Autowired Actions action;
	
	
	@Override
	public void addToCart(Request request,
	        StreamObserver<ListOfItems> responseObserver) {
	      
		Long productId = request.getProductId();
		Long quantity = request.getQuantity();
		Long cartId= request.getCartId();
		
			//GET THE PRODUCT
			Product product = mapper.getProduct(productId);
			
			//CHECKING ERRORS
			action.checkProductExistence(product); //under product table
			action.checkStocks(product);
			action.checkQuantity(quantity, product);
			
			//BOOKING THE PRODUCT
			//lessening the stocks by the item's quantity
			mapper.updateProductStocks(product.getStocks()-quantity, product.getProductId()); //update the product's stocks
			
			
			Cartitem cart = new Cartitem(
					productId,
					quantity,
					product.getPrice()*quantity,
					cartId
					);
			
			
			//CHECK IF PRODUCT IS ALREADY EXISTING UNDER THE SPECIFIED CART ID
			int count = mapper.checkProductExistence(cartId, productId); //under cart table
			
			//IF EXISTING ---> UPDATE
			if(count>0) {
				Cartitem item = mapper.getCartItemByProduct(cartId, productId);
				item.setQuantity(cart.getQuantity()+item.getQuantity());
				item.setSubTotal(cart.getSubTotal()+item.getSubTotal());
				mapper.updateCart(item);
			}
			
			//IF NOT EXISTING ---> CREATE
			else {
				mapper.create(cart);
			}
			
			action.getList(request, responseObserver); //returning the list
	    }

	
	//LIST ALL ITEMS IN A PARTICULAR CART
	@Override
	public void listAllItems(Request request,
	        StreamObserver<ListOfItems> responseObserver) {
		action.getList(request, responseObserver);
	}
	
	
	//DELETE A PRODUCT FROM THAT CART
	@Override
	public void deleteItem(Request request,
	        StreamObserver<ListOfItems> responseObserver) {
		//GETTING NECESSARRY VALUES
		Long productId = request.getProductId();
		Long cartId= request.getCartId();
		
		//CHECKING ERRORS
		action.checkCartExistence(cartId);
		action.checkProductExistence(mapper.getProduct(productId));
		action.checkProductUnderCart(cartId, productId);
		
		//Get Necessary details
		Product product = mapper.getProduct(productId);
		Cartitem oldDetails = mapper.getCartItemByProduct(cartId, productId);
		
		//Update the product stocks before deleting the item
		mapper.updateProductStocks(
				product.getStocks()+oldDetails.getQuantity(), 
				product.getProductId());
		
		//Delete the item
		mapper.delete(cartId, productId);
		
		//Return the list of items left
		action.getList(request, responseObserver);
	}
	
	

	@Override
	public void updateItemOnTheCart(Request request,
	        StreamObserver<ListOfItems> responseObserver) {
		
		//GETTING NECESSARRY VALUES
		Long productId = request.getProductId();
		Long quantity = request.getQuantity();
		Long cartId= request.getCartId();
		
		//GET THE PRODUCT
		Product product = mapper.getProduct(productId);
				
		//CHECKING ERRORS
		action.checkCartExistence(cartId);
		action.checkProductExistence(product);
		action.checkProductUnderCart(cartId, productId);
		
		//Check if the request's quantity of product is greater than or less than or still the same with the old quantity
		Cartitem oldDetails = mapper.getCartItemByProduct(cartId, productId);
		Long oldQuantity = oldDetails.getQuantity();
		Long newQuantity = quantity;
		
		
		if(newQuantity>0 && !Objects.equals(newQuantity, oldQuantity)) {
			
			if(newQuantity>oldQuantity) {
				Long difference = newQuantity-oldQuantity;
				action.checkQuantity(difference, product); 
				product.setStocks(product.getStocks()-difference);	
			}
			
			else{	
				Long difference = oldQuantity-newQuantity;
				product.setStocks(product.getStocks()+difference);	
			}
			
			mapper.updateProductStocks(product.getStocks(), product.getProductId()); 	//OK
			oldDetails.setQuantity(newQuantity);
			oldDetails.setSubTotal(newQuantity*product.getPrice());
			
			mapper.updateCart(oldDetails);
		}
		
		else 
			//If the newQuantity requested is less than or equal to zero means customer wanted to remove the item from the cart
			mapper.delete(cartId, productId);
		
		
		action.getList(request, responseObserver);		
	}

	
	@Override
	public void checkOut(Order request, StreamObserver<Order> responseObserver) {
		
		//CHECKING ERRORS
		action.checkCartExistence(request.getCartId());
		
		//IF THERES NO ERRORS, PROCEED
		List<Item> lists = new ArrayList<>();
		mapper.findAll(request.getCartId()).forEach(cartitem->{
			lists.add(Item.newBuilder()
					.setProductId(cartitem.getProductId())
					.setQuantity(cartitem.getQuantity())
					.setSubTotal(cartitem.getSubTotal())
					.build());
		});
		
		Order response = Order.newBuilder()
				.setCartId(request.getCartId())
				.setCourier(request.getCourier())
				.addAllItems(lists)
				.build();
		
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        
        //IF FETCHED LISTS IS NOT EMPTY, REMOVE ALL PRODUCTS FROM THE SPECIFIED CART
      		if(!lists.isEmpty())
      			lists.forEach(item->{
      				mapper.delete(request.getCartId(), item.getProductId());
      			});
	}
}
