package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderSearch;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;
	
	
	//������ ����
	public void save(Order order) {
		em.persist(order);
	}
	
	//������ �ܰ� ��ȸ
	public Order findOne(Long id) {
		return em.find(Order.class, id);  
	}
	
	
	//������ �ٰ� ��ȸ
	public List<Order> findAll(OrderSearch orderSearch){
		
		return em.createQuery("select o from Order o join o.member m"
				+ " where o.status = :status "
				+ " and m.name like :name", Order.class)
				.setParameter("status", orderSearch.getOrderStatus())
				.setParameter("name", orderSearch.getMemberName())
				.setMaxResults(1000) //�ִ� 1000�Ǥ�
				.getResultList();
	}
	
}
