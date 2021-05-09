package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orderItem")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
	
	@Id
	@GeneratedValue
	@Column(name = "order_item_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice;  //주문가격
	private int count; 		 //주문 수량
	
//	protected OrderItem() {}
	
	//==생성 메서드 ==//
	public static OrderItem createOrderItem(Item item, int orderprice, int count) {
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderprice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		return orderItem;
	}
	
	
	
	//== 비지니스 로직 ==//
	public void cancel() {
		getItem().addStock(count);
	}

	public int getOrderTotalPrice() {
		return getOrderPrice() * getCount();
	}

}
