package com.example.demo.controller;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    //@NotEmpty(message = "name�� �ʼ����Դϴ�.") private String name;
	private String name;
    private String city;
    private String street;
    private String zipcode;
}
