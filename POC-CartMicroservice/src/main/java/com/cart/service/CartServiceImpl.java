package com.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.cart.dto.ProductQuantity;
import com.cart.dto.RequestModel;
import com.cart.model.Cartitem;
import com.cart.model.Product;

@Service
public class CartServiceImpl extends CartService{

	//ADDING TO CART
	@Override
	public Object addToCart(RequestModel request) {
		
		//GET THE PRODUCT
		Product product = mapper.getProduct(request.getProductId());
		
		//CHECKING ERRORS
		checkProductExistence(product); //under product table
		checkIfParseable(request.getQuantity());
		checkStocks(product);
		checkQuantity(Long.parseLong(request.getQuantity()), product);
		
		//BOOKING THE PRODUCT
		product.setStocks(product.getStocks()-Long.parseLong(request.getQuantity()));	//lessening the stocks by the item's quantity
		mapper.updateProductStocks(product.getStocks(), product.getProductId()); //update the product's stocks
		
		
		Cartitem cart = new Cartitem(
				request.getProductId(),
				Long.parseLong(request.getQuantity()),
				product.getPrice()*Long.parseLong(request.getQuantity()),
				request.getCartId()
				);
		
		
		//CHECK IF PRODUCT IS ALREADY EXISTING UNDER THE SPECIFIED CART ID
		int count = mapper.checkProductExistence(request.getCartId(), request.getProductId()); //under cart table
		
		//IF EXISTING ---> UPDATE
		if(count>0) {
			Cartitem item = mapper.getCartItemByProduct(request);
			item.setQuantity(cart.getQuantity()+item.getQuantity());
			item.setSubTotal(cart.getSubTotal()+item.getSubTotal());
			mapper.updateCart(item);
			return listAllItems(request.getCartId());
		}
		
		//IF NOT EXISTING ---> CREATE
		else {
			mapper.create(cart);
			return listAllItems(request.getCartId());
		}
	}
	
	//LIST ALL ITEMS IN A PARTICULAR CART
	@Override
	public Object listAllItems(Long cartId) {
		return mapper.findAll(cartId);
	}
	
	
	//DELETE A PRODUCT FROM THAT CART
	@Override
	public Object deleteItem(Long cartId, Long productId) {
		//CHECKING ERRORS
		checkCartExistence(cartId);
		checkProductExistence(mapper.getProduct(productId));
		checkProductUnderCart(cartId, productId);
		
		//Get Necessary details
		RequestModel request = new RequestModel(cartId, productId, null);
		Product product = mapper.getProduct(productId);
		Cartitem oldDetails = mapper.getCartItemByProduct(request);
		
		//Update the product stocks before deleting the item
		product.setStocks(product.getStocks()+oldDetails.getQuantity());
		mapper.updateProductStocks(product.getStocks(), product.getProductId());
		
		//Delete the item
		checkIfOperationIsSuccess(mapper.delete(cartId, productId));
		
		//Return the list of items left
		return listAllItems(cartId);
	}
	
	

	@Override
	public Object updateItemOnTheCart(RequestModel request) {
		//Check if the request's quantity of product is greater than or less than or still the same with the old quantity
		
		//GET THE PRODUCT
		Product product = mapper.getProduct(request.getProductId());
				
		//CHECKING ERRORS
		checkCartExistence(request.getCartId());
		checkProductExistence(product);
		checkProductUnderCart(request.getCartId(), request.getProductId());
		checkIfParseable(request.getQuantity());
		
		Cartitem oldDetails = mapper.getCartItemByProduct(request);
		Long oldQuantity = oldDetails.getQuantity();
		Long newQuantity = Long.parseLong(request.getQuantity());
		
		
		if(newQuantity>0) {
			if(Objects.equals(newQuantity, oldQuantity)) {
				return listAllItems(request.getCartId());
			}
			else if(newQuantity>oldQuantity) {
				Long difference = newQuantity-oldQuantity;
				checkQuantity(difference, product); 
				product.setStocks(product.getStocks()-difference);	
			}
			
			else{	
				Long difference = oldQuantity-newQuantity;
				product.setStocks(product.getStocks()+difference);	
			}
			
			checkIfOperationIsSuccess(mapper.updateProductStocks(product.getStocks(), product.getProductId())); 	//OK
			oldDetails.setQuantity(newQuantity);
			oldDetails.setSubTotal(newQuantity*product.getPrice());
			
			checkIfOperationIsSuccess(mapper.updateCart(oldDetails));
			
			return listAllItems(request.getCartId());
		}
		
		//If the newQuantity requested is less than or equal to zero means customer wanted to remove the item from the cart
		return deleteItem(request.getCartId(), request.getProductId());
		
	}

	
	@Override
	public Object checkOut(Long cartId) {
		//CHECKING ERRORS
		checkCartExistence(cartId);
		
		//IF ERRORS PROCEED
		List<Cartitem> items = mapper.findAll(cartId);
		List<ProductQuantity> products = new ArrayList<>();
		if(items.isEmpty()) 
			return items;
		items.forEach(item ->{
			products.add(new ProductQuantity(item.getProductId(), item.getQuantity(), item.getSubTotal()));
			//For checking out the items of this cart , each item should be deleted
			mapper.delete(cartId, item.getProductId());
		});
		
		return products;
	}
	
	
	
	
}
