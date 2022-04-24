package com.product.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name="shippinginformation")
public class Shippinginformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shippingid;
	
	@Column
	private String trackingNumber;	//randomized your own, within the logic and should be 10 digits
	
	@Column
	private String courier;  //Company that delivers.
	
	@Enumerated
	private ShippingStatus status; 
	
	@Column
	private String eta; //Estimated Time of Arrival	//should be 3 days from the day of order creation
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date timestamp;
}
