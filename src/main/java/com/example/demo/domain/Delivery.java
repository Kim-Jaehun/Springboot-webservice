package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery")
@Setter
@Getter
public class Delivery {
	

	@Id
	@GeneratedValue
	private long id;

	@OneToOne
	private Order order;
	
	private DeliveryStatus status;
	
	private Address address;
	
}
