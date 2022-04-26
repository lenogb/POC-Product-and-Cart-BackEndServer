package com.cartgateway.servercomm.grpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cart.grpc.generated_sources.CartServiceGrpc.CartServiceBlockingStub;
import com.cart.grpc.generated_sources.ListResponse;
import com.cart.grpc.generated_sources.Request;
import com.cart.grpc.generated_sources.StringResult;
import com.cart.grpc.generated_sources.User;
import com.cartgateway.dtos.RequestInfo;
import com.cartgateway.dtos.ResponseInfo;
import com.cartgateway.enums.ServerStatus;

@Component
public class CartMicroserviceCommunicator {

	@Autowired CartServiceBlockingStub stub;

	public List<ResponseInfo> addToCart(RequestInfo request, String principal) {
    	ListResponse response = stub.addToCart(Request.newBuilder()
    			.setUsername(User.newBuilder().setUsername(principal).build())
    			.setProductId(request.getProductId())
    			.setQuantity(request.getQuantity())
    			.build());
    	return mapList(response);
    }
    
    public List<ResponseInfo> listAll(String username) {
    	ListResponse response = stub.getAllItems(User.newBuilder()
    			.setUsername(username)
    			.build());
    	return mapList(response);
    }
    
    public List<ResponseInfo> removeItem(RequestInfo request,String principal) {
    	ListResponse response = stub.removeItem(Request.newBuilder()
    			.setUsername(User.newBuilder().setUsername(principal).build())
    			.setProductId(request.getProductId())
    			.build());
    	return mapList(response);
    }
    
    public String checkOut(String user) {
    	StringResult result = stub.checkOut(User.newBuilder().setUsername(user).build());
    	return result.getMessage();
    }
    
    
    //MAPPER METHODS
  	public List<ResponseInfo> mapList(ListResponse object){
      	List<ResponseInfo> list = new ArrayList<>();
      	for(int i=0; i<object.getItemsCount(); i++) {
      		list.add(new ResponseInfo(
      				object.getItems(i).getProductId(),
      				object.getItems(i).getName(),
      				object.getItems(i).getPrice(),
      				object.getItems(i).getQuantity(),
      				ServerStatus.FINE.name()
      				));
      	}
      	return list;
      }
	
	
}
