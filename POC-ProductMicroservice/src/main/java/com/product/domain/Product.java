package com.product.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
	private String category;
	private String name;
	private String code;
	private String description;
	private Double price;
	private Long stocks;
	private Long booked;
	private Long checkedout;
	private Long available;
	
	public Product(String category,String name,String code) {
		this.category=category;
		this.name=name;
		this.code=code;
	}
	
	public Product(Long productId) {
		super();
		this.productId = productId;
	}
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date",updatable = false)
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifiedDate;

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", category=" + category + ", name=" + name + ", code=" + code
				+ ", description=" + description + ", price=" + price + ", stocks=" + stocks + ", booked=" + booked
				+ ", checkedout=" + checkedout + ", available=" + available + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	
	
//	@PrePersist
//	private void ensureId(){
//	    this.setProductId(UUID.randomUUID().toString());
//	}
}
