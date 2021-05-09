package com.example.demo.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum OrderStatus {
	ORDER
	,CANCEL;
}
