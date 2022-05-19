package com.cartgateway.servercomm.grpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cart.grpc.generated_sources.CartServiceGrpc.CartServiceBlockingStub;
import com.cart.grpc.generated_sources.ListResponse;
import com.cart.grpc.generated_sources.Request;
import com.cart.grpc.generated_sources.User;
import com.cartgateway.dtos.RequestInfo;
import com.cartgateway.dtos.ResponseInfo;
import com.cartgateway.enums.Problem;
import com.cartgateway.servercomm.http.OrderRequest;
import com.cartgateway.servercomm.http.ProductMicroserviceCommunicator;

@Component
public class CartMicroserviceCommunicator {

	@Autowired CartServiceBlockingStub stub;
	@Autowired ProductMicroserviceCommunicator pm;

	public List<ResponseInfo> addToCart(RequestInfo request, String principal) throws IOException, InterruptedException {
		
		pm.sendRequestToProductMicroservice("/cartRelatedOperations/book",request);
		
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
    
    public List<ResponseInfo> removeItem(RequestInfo request,String principal) throws IOException, InterruptedException {
    	
    	pm.sendRequestToProductMicroservice("/cartRelatedOperations/remove", request);
    	
    	ListResponse response = stub.removeItem(Request.newBuilder()
    			.setUsername(User.newBuilder().setUsername(principal).build())
    			.setProductId(request.getProductId())
    			.build());
    	return mapList(response);
    }
    
    public void checkOut(String user, OrderRequest order) throws IOException, InterruptedException {
    	
    	List<ResponseInfo> lists= listAll(user);
		Map<Long, Long> itemInfo = lists.stream()
				.collect(Collectors.toMap(ResponseInfo::getProductId, ResponseInfo::getQuantity));

		order.setItemDetails(itemInfo);
		order.setConsumer(user);
    	
    	stub.checkOut(User.newBuilder().setUsername(user).build());
    	
    	pm.sendRequestToProductMicroservice("/processOrder/order", order);
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
      				Problem.OK.name()
      				));
      	}
      	return list;
      }
}
