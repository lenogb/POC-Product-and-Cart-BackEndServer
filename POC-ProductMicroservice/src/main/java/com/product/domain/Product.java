package com.product.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private Long available;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", updatable = false)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date modifiedDate;

	public Product(String category, String name, String code) {
		this.category = category;
		this.name = name;
		this.code = code;
	}

	public Product(Long productId) {
		super();
		this.productId = productId;
	}

    public static Product merge(Product o, Product u) {
		if(!u.getCategory().equals(o.getCategory()))
			o.setCategory(u.getCategory());
		if(!u.getName().equals(o.getName()))
			o.setName(u.getName());
		if(!u.getCode().equals(o.getCode()))
			o.setCode(u.getCode());
		if(!u.getDescription().equals(o.getDescription()))
			o.setDescription(u.getDescription());
		if(u.getPrice()!=o.getPrice())
			o.setPrice(u.getPrice());
		if(u.getStocks()!=o.getStocks())
			o.setStocks(u.getStocks());
		return o;
    }

    @Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", category='" + category+ '\'' +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", stocks=" + stocks +
				", booked=" + booked +
				", available=" + available +
				", createDate=" + createDate +
				", modifiedDate=" + modifiedDate +
				'}';
	}

	@PrePersist
	void runBeforePersisting(){
		setBooked(0l);
		setAvailable(getStocks()-getBooked());
	}

	public static Boolean isEqual(Product p1 , Product p2){
		if(
			p1.getName().equalsIgnoreCase(p2.getName()) &&
			p1.getCode().equalsIgnoreCase(p2.getCode()) &&
			p1.getCategory().equalsIgnoreCase(p2.getCategory()) &&
			p1.getDescription().equalsIgnoreCase(p2.getDescription()) &&
			p1.getPrice() == p2.getPrice() &&
			p1.stocks == p2.getStocks()
		)
			return true;
		return false;
	}
}