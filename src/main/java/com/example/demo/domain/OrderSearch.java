package com.example.demo.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
	private String memberName; 
	private OrderStatus orderStatus;
}
