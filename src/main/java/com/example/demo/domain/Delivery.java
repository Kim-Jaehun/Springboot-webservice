package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery")
@Setter
@Getter
public class Delivery {

	private Order order;
	
	private String status;
}
