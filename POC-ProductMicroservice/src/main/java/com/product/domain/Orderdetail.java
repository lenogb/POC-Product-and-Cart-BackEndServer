package com.product.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orderdetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
	private Long cartId;	//CUSTOMER ID
	private Double total;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="shippingid")
	private Shippinginformation shippinginfo;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;
}
