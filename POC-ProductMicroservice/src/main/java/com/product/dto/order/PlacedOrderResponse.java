package com.product.dto.order;

import java.util.Date;
import java.util.List;

import com.product.domain.Shippinginformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlacedOrderResponse {

	private Long orderId;
    private List<Productdetails> items;
    private Double total;
    private Date orderTime;
    private Shippinginformation shippinginfo;
    
}
