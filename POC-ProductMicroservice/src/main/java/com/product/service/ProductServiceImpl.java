package com.product.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.domain.Orderdetail;
import com.product.domain.Product;
import com.product.domain.PurchasedHistory;
import com.product.domain.Shippinginformation;
import com.product.dto.order.OrderRequest;
import com.product.dto.order.RequestInfo;
import com.product.enums.ShippingStatus;

@Service
public class ProductServiceImpl extends ProductService {
	@Autowired ModelMapper mapper;
	
	@Override
	public Object save(Object product){
		return repository.save( product);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return repository.getAllProducts();
	}
	
	@Override
	public Object getProduct(Long id) {
		return repository.getProduct(id);
	}
	
	@Override
	public Product updateProduct(Product updated) {
		repository.updateProduct(updated);
		return updated;
	}
	
	@Override
	public Orderdetail createOrder(OrderRequest request, String consumer) {
		Double total=0d;
		
		for(RequestInfo item : request.getItemDetails()) {
			Product fullDetails = (Product) getProduct(item.getProductId());
			fullDetails.setBooked(fullDetails.getBooked()-item.getQuantity());
			fullDetails.setStocks(fullDetails.getStocks()-item.getQuantity());
			//products that are available are considered as not yet booked
			fullDetails.setAvailable(fullDetails.getStocks()-fullDetails.getBooked());
			fullDetails.setCheckedout(fullDetails.getCheckedout()+item.getQuantity());
			updateProduct(fullDetails);
			total=+(fullDetails.getPrice()*item.getQuantity());
		}

		Orderdetail order= new Orderdetail();
		Shippinginformation shipping = new Shippinginformation();
		
		shipping.setCourier(request.getShippingCourier());
		shipping.setTrackingNumber(generateTracking(10));
		shipping.setStatus(ShippingStatus.TOSHIP);
		LocalDateTime now = LocalDateTime.now().plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		shipping.setEta(now.format(formatter));
		
		order.setConsumer(consumer);
		order.setTotal(total);
		order.setShippinginfo(shipping);
		
		return (Orderdetail) repository.save(order);
	}
	
	public List<PurchasedHistory> getAllPurchasedItems(String loggedInUser, Date orderTime) {
		return repository.getAllPurchasedItems(loggedInUser, orderTime);
	}

//	public Object getAllOrders(String loggedInUser) {
//		List<Orderdetail> orders = repository.getAllOrders(loggedInUser);
//		List<OrderResponse> response = new ArrayList<>();
//		
//		for(Orderdetail order : orders) {
//			var placedOrder = mapper.map(order, OrderResponse.class);
//			
//			List<OrderResponse.ProductDetails> items = new ArrayList<>();
//			List<PurchasedHistory> listOfPurchasedItems = getAllPurchasedItems(loggedInUser, order.getOrderTime());
//			for(PurchasedHistory purchased : listOfPurchasedItems) {
//				items.add(mapper.map(getProduct(purchased.getProductId()), OrderResponse.ProductDetails.class));
//			}
//			placedOrder.setItems(items);
//			response.add(placedOrder);
//		}
//		return response;
//	}

}
