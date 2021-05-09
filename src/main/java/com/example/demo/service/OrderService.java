package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Delivery;
import com.example.demo.domain.Item;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;

@Service
@Transactional(readOnly = true)
public class OrderService {
	
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	OrderRepository orderRepository;
	
	//�ֹ� 
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		
		//��ƼƼ ��ȸ
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		//������� ����
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		//�ֹ���ǰ ����
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//�ֹ� ����
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//�ֹ� ����
		orderRepository.save(order);
		
		return order.getId();
	}
	
	//�ֹ����
	@Transactional
	public void cancelOrder(Long orderId) {
		
		//�ֹ� ��ƼƼ ��ȸ
		Order order = orderRepository.findOne(orderId);
		
		//�ֹ� ���
		order.cancel();
	}
	
	//�ֹ� �˻�
	
}
