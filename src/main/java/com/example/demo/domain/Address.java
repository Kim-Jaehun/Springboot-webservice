package com.example.demo.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
	private String city;
	private String street;
	private String zipcode;
	
	protected Address() {}  // jpa spec에 맞춰주기 위해 기본생성자를 만든다. private이 허용이 안되서 protected로 선언.

	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	
	
}
