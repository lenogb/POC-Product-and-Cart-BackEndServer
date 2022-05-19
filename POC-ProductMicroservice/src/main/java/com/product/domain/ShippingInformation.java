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
@Table(name="shippingInformation")
public class ShippingInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shippingId;
	private String trackingNumber;	//randomized your own, within the logic and should be 10 digits
	private String courier;  //Company that delivers.
	@Enumerated
	private ShippingStatus status; 
	private String eta; //Estimated Time of Arrival	//should be 3 days from the day of order creation
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date timestamp;

	public ShippingInformation(String trackingNumber, String courier, ShippingStatus status, String eta) {
		this.trackingNumber = trackingNumber;
		this.courier = courier;
		this.status = status;
		this.eta = eta;
	}
}
