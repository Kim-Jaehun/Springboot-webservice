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
	public void 상품주문() throws Exception {
		//given
		
		//회원등록
		Member member = new Member();
		member.setUserName("회원1");
		member.setAddress(new Address("aaa", "bbb", "12314"));
		em.persist(member);
		
		Book book = new Book();
		book.setName("수학의 정석");
		book.setPrice(10000);
		book.setStockQuantity(100);
		em.persist(book);
		
		//when
		int orderCount = 10;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("상품주문시 Order 상태  ", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량", 10000 * orderCount, getOrder.getTotalPrice());
		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 10, book.getStockQuantity());
		
		
	}	 
	
	@Test(expected = NotEnoughStockException.class)
	@Ignore
	public void 상품주문_재고수량초과() throws Exception {
		//given
		Member member = new Member();
		member.setUserName("회원1");
		member.setAddress(new Address("aaa", "bbb", "12314"));
		em.persist(member);
		
		Book book = new Book();
		book.setName("수학의 정석");
		book.setPrice(10000);
		book.setStockQuantity(100);
		em.persist(book);
		
		 
		//when
		int orderStock = 111;
		orderService.order(member.getId(), book.getId(), orderStock);
		
		//then
		fail("재고 수량 예외가 발생하여야 한다.");
		
	}
	
	@Test 
	@Ignore
	public void 삼품취소() throws Exception {
		//given
		Member member = new Member();
		member.setUserName("회원1");
		member.setAddress(new Address("aaa", "bbb", "12314"));
		em.persist(member);
		
		Book book = new Book();
		book.setName("수학의 정석");
		book.setPrice(10000);
		book.setStockQuantity(100);
		em.persist(book);		
		
		int orderStock = 11;
		Long orderId = orderService.order(member.getId(), book.getId(), orderStock);
		
		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		assertEquals("주문 취소시 상태는 cancel이다", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.",
				100, book.getStockQuantity());
			
	}
	

}
