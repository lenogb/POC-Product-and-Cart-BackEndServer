package com.product.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.product.domain.OrderDetail;
import com.product.domain.Product;
import com.product.domain.PurchasedProducts;
import com.product.domain.ShippingInformation;
import com.product.dto.ProductResponse;
import com.product.dto.order.OrderRequest;
import com.product.enums.ShippingStatus;
import com.product.exception.ProductException;

@Service
public class ProductServiceImpl extends ProductService {
	ModelMapper mapper= new ModelMapper();

	public Product saveProduct(Product product){
		//Check for conflicts
		checkConflicts(product);
		
		return repository.saveProduct(product);
	}

    public OrderDetail saveOrder(OrderDetail order){
		return repository.saveOrder(order);
	}

	public List<Product> getAllProducts() {
		return repository.getAllProducts();
	}

	public Product getProduct(Long id) {
		return repository.getProductById(id);
	}

	public Product updateProduct(Long id, Product updatedProduct) {
		//GETTING THE PRODUCT
		Product originalProduct = getProduct(id);

		//IF CHANGES DETECTED
		if(Boolean.FALSE.equals(Product.isEqual(originalProduct, updatedProduct)))  {

			//VALIDATING NAME AND CODE IF THERE ARE ANY CONFLICTS
			Boolean nameChanged = Boolean.FALSE.equals(originalProduct.getName().equals(updatedProduct.getName()));
			Boolean codeChanged = Boolean.FALSE.equals(originalProduct.getCode().equals(updatedProduct.getCode()));
	
			if(Boolean.TRUE.equals(nameChanged))
				checkConflicts(
					new Product(updatedProduct.getCategory(), updatedProduct.getName(), null));
			if(Boolean.TRUE.equals(codeChanged))
				checkConflicts(
					new Product(updatedProduct.getCategory(), null , updatedProduct.getCode()));
	
			//VALIDATING NEW STOCKS
			if(updatedProduct.getStocks()<originalProduct.getBooked())
				throw new ProductException(
					"Invalid Stock Quantity Request Input: Refer to the below Information:\n"
					+ "*Reserved Quantity of Products: "+originalProduct.getBooked()+"\n"
					+ "The requested quantity of new stock is less than the reserved products which should not be affected");
			
			Product upd = repository.update(Product.merge(originalProduct, updatedProduct));
			
			return upd;
		}

		return originalProduct;
	}
	
	public ProductResponse updateProduct(Product updatedProduct) {
		Product upd = repository.update(updatedProduct);
		return mapper.map(upd, ProductResponse.class);
	}
	

	public OrderDetail createOrder(OrderRequest request, String consumer) {
		//RETRIEVING AND CREATING OBJECTS NECESSARY FOR ORDER CREATION

		AtomicReference<Double> total = new AtomicReference<>(0d);
		Map<Long, Long> items = request.getItemDetails();
		List<PurchasedProducts> purchasedProductList = new ArrayList<>();

		items.keySet()
				.stream()
				.forEach(
						productId ->
						{
							Product p = getProduct(productId);
							Long qty = items.get(productId);
							p.setBooked(p.getBooked()-qty);
							p.setStocks(p.getStocks()-qty);
							updateProduct(p);
							total.set(total.get() + (p.getPrice() * qty));
							purchasedProductList.add(new PurchasedProducts(qty, p));
						}
				);

		//CREATING NEW ORDER

		OrderDetail order= new OrderDetail();

		order.setProducts(purchasedProductList);
		order.setConsumer(consumer);
		order.setTotal(total.get());

		LocalDateTime now = LocalDateTime.now().plusDays(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		order.setShippingInfo(
				new ShippingInformation(
						generateTracking(10),
						request.getShippingCourier(),
						ShippingStatus.TOSHIP,
						now.format(formatter))
		);

		return repository.saveOrder(order);
	}

	public List<OrderDetail> getAllOrders(String loggedInUser) {
		return repository.getAllOrders(loggedInUser);
	}

}
