package com.poc.microservices.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart_poc_microservice_entity")
@Getter
@Setter
@NoArgsConstructor
public class Cart {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;
}
