package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private Address address;
	
	@OneToMany
	List<Order> orders = new ArrayList<Order>();

}
