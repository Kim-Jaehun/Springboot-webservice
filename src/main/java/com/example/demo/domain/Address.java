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
	
	protected Address() {}  // jpa spec�� �����ֱ� ���� �⺻�����ڸ� �����. private�� ����� �ȵǼ� protected�� ����.

	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	
	
}
