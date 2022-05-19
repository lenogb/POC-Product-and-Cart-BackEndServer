package com.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orderDetail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
	private String consumer;	
	private Double total;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", referencedColumnName = "orderId")
	private List<PurchasedProducts> products;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="shippingId")
	private ShippingInformation shippingInfo;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;

	@Override
	public String toString() {
		return "Orderdetail [orderId=" + orderId + ", consumer=" + consumer + ", total=" + total + ", shippingInfo="
				+ shippingInfo + ", orderTime=" + orderTime + "]";
	}
	
	
	
}
