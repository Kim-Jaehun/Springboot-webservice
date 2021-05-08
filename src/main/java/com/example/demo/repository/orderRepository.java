package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class orderRepository {
	
	private final EntityManager em;
	
	
	//아이템 저장
	public void save(Order order) {
		em.persist(order);
	}
	
	//아이템 단건 조회
	public Order findOne(Long id) {
		return em.find(Order.class, id);  
	}
	
	
	//아이템 다건 조회
	public List<Order> findAll(){
		return em.createQuery("select i from Order i", Order.class)
				.getResultList();
	}
	
}
