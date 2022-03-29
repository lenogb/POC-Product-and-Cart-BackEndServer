package com.product.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.product.enums.ShippingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shippinginformation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shippingid;
	
	@Column(updatable = false)
	private Long trackingNumber;	//randomized your own, within the logic and should be 10 digits
	
	@Column(updatable = false)
	private String courier;  //Company that delivers.
	
	private ShippingStatus status; 
	
	@Column(updatable = false)
	private String eta; //Estimated Time of Arrival	//should be 3 days from the day of order creation
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date timestamp;
}
