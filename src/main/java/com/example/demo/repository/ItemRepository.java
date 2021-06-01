package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	
	private final EntityManager em;
	
	
	//아이템 저장
	public void save(Item item) {
//		if(item.getId() == null) {
//			em.persist(item);
//		}else {
//			em.merge(item);
//		}
		em.persist(item);
	}
	
	//아이템 단건 조회
	public Item findOne(Long id) {
		return em.find(Item.class, id);  
	}
	
	
	//아이템 다건 조회
	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
	
}
