package com.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.cart.enums.RequestError;
import com.cart.exception.NoItemsYetException;
import com.cart.grpc.generated_sources.CartServiceGrpc.CartServiceImplBase;
import com.cart.grpc.generated_sources.ListResponse;
import com.cart.grpc.generated_sources.ProductDetails;
import com.cart.grpc.generated_sources.Request;
import com.cart.grpc.generated_sources.StringResult;
import com.cart.grpc.generated_sources.User;
import com.cart.mapper.CartMapper;
import com.cart.model.Cart;
import com.cart.model.Cartitem;
import com.cart.model.Product;
import com.cart.util.ErrorChecker;

import io.grpc.stub.StreamObserver;
import lombok.NoArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
@NoArgsConstructor
public class CartServiceImpl extends CartServiceImplBase{
	
	@Autowired CartMapper mapper;	
	@Autowired ErrorChecker check;
	ModelMapper modelmapper;
	
	public CartServiceImpl(ModelMapper modelmapper) {
		super();
		this.modelmapper = modelmapper;
	}
	
	private Long getCartId(String username) {
		int cartInstances = mapper.checkCart(username);
		if(cartInstances>0) 
			return mapper.getCartIdByCustomer(username);
		else {
			mapper.createCart(new Cart(username));
			return mapper.getCartIdByCustomer(username);
		}
	}
	
	public void getList(Long cartId, StreamObserver<ListResponse> responseObserver){
		List<ProductDetails> lists = new ArrayList<>();
		
		mapper.findAll(cartId).forEach(cartitem->{
			Product product = mapper.getProduct(cartitem.getProductId());
			lists.add(ProductDetails.newBuilder()
					.setProductId(product.getProductId())
					.setName(product.getName())
					.setCode(product.getCode())
					.setPrice(product.getPrice())
					.setQuantity(cartitem.getQuantity())
					.setSubTotal(cartitem.getSubTotal())
					.build());
		});

		responseObserver.onNext(ListResponse.newBuilder()
				.addAllItems(lists)
				.build());
        responseObserver.onCompleted();
	}
	
	@Override
	public void addToCart(Request request, StreamObserver<ListResponse> responseObserver) {
		Long productId = request.getProductId();
		Long quantity = request.getQuantity();
		String user = request.getUsername().getUsername();
		
		//GET THE PRODUCT
		Product product = mapper.getProduct(productId);
		
		//CHECKING ERRORS
		check.checkAvailability(product);	//checking availability
		
		Long cartId = getCartId(user);
		
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
			Cartitem item = mapper.getItem(cartId, productId);
			item.setQuantity(cart.getQuantity()+item.getQuantity());
			item.setSubTotal(cart.getSubTotal()+item.getSubTotal());
			mapper.updateCart(item);
		}
				
		//IF NOT EXISTING ---> CREATE
		else mapper.addToCart(cart);
		getList(cartId, responseObserver); 
	}
	
	@Override
	public void getAllItems(User request, StreamObserver<ListResponse> responseObserver) {
		Long cartId = getCartId(request.getUsername());
		getList(cartId, responseObserver);
	}


	@Override
	public void removeItem(Request request, StreamObserver<ListResponse> responseObserver) {
		Long cartId = getCartId(request.getUsername().getUsername());
		
		//CHECKING ERRORS
		check.checkProductUnderCart(cartId, request.getProductId());
		
		mapper.remove(cartId, request.getProductId());
		getList(cartId, responseObserver);
	}

	

	@Override
	public void checkOut(User request, StreamObserver<StringResult> responseObserver) {
		Long cartId = getCartId(request.getUsername());
		List<Cartitem> lists = mapper.findAll(cartId);
		
        //IF FETCHED LISTS IS NOT EMPTY, REMOVE ALL PRODUCTS FROM THE SPECIFIED CART
      	if(!lists.isEmpty())
      		lists.forEach(item->{
      			mapper.remove(cartId, item.getProductId());
      	});
      	else 
      		throw new NoItemsYetException(
					RequestError.UNACCEPTABLEINPUT,
					"Your cart doesn't have yet items. Request for Check-out is cancelled");
      	
      	responseObserver.onNext(StringResult.newBuilder().setMessage("SUCCESS").build());
      	responseObserver.onCompleted();
	}

	

}
