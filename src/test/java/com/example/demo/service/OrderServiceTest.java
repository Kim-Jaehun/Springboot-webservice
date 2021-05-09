 package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Address;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.exception.NotEnoughStockException;
import com.example.demo.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional 
public class OrderServiceTest {
	
	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;
	
	
	@Test
	public void ��ǰ�ֹ�() throws Exception {
		//given
		
		//ȸ�����
		Member member = new Member();
		member.setUserName("ȸ��1");
		member.setAddress(new Address("aaa", "bbb", "12314"));
		em.persist(member);
		
		Book book = new Book();
		book.setName("������ ����");
		book.setPrice(10000);
		book.setStockQuantity(100);
		em.persist(book);
		
		//when
		int orderCount = 10;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("��ǰ�ֹ��� Order ����  ", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("�ֹ��� ��ǰ ���� ���� ��Ȯ�ؾ� �Ѵ�.", 1, getOrder.getOrderItems().size());
		assertEquals("�ֹ� ������ ���� * ����", 10000 * orderCount, getOrder.getTotalPrice());
		assertEquals("�ֹ��� ��ǰ ���� ���� ��Ȯ�ؾ� �Ѵ�.", 10, book.getStockQuantity());
		
		
	}	 
	
	@Test(expected = NotEnoughStockException.class)
	@Ignore
	public void ��ǰ�ֹ�_�������ʰ�() throws Exception {
		//given
		Member member = new Member();
		member.setUserName("ȸ��1");
		member.setAddress(new Address("aaa", "bbb", "12314"));
		em.persist(member);
		
		Book book = new Book();
		book.setName("������ ����");
		book.setPrice(10000);
		book.setStockQuantity(100);
		em.persist(book);
		
		 
		//when
		int orderStock = 111;
		orderService.order(member.getId(), book.getId(), orderStock);
		
		//then
		fail("��� ���� ���ܰ� �߻��Ͽ��� �Ѵ�.");
		
	}
	
	@Test 
	@Ignore
	public void ��ǰ���() throws Exception {
		//given
		Member member = new Member();
		member.setUserName("ȸ��1");
		member.setAddress(new Address("aaa", "bbb", "12314"));
		em.persist(member);
		
		Book book = new Book();
		book.setName("������ ����");
		book.setPrice(10000);
		book.setStockQuantity(100);
		em.persist(book);		
		
		int orderStock = 11;
		Long orderId = orderService.order(member.getId(), book.getId(), orderStock);
		
		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		assertEquals("�ֹ� ��ҽ� ���´� cancel�̴�", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("�ֹ��� ��ҵ� ��ǰ�� �׸�ŭ ��� �����ؾ� �Ѵ�.",
				100, book.getStockQuantity());
			
	}
	

}
