package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderItem")
@Setter
@Getter
public class OrderItem {
	
	private Order order;

}
