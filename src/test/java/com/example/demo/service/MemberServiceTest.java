 package com.example.demo.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	EntityManager em;

	@Test
	//@Rollback(false)
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setUserName("kimjaehun");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		em.flush();
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setUserName("kimjaehun");
		 
		Member member2 = new Member();
		member2.setUserName("kimjaehun");
		
		//when
		memberService.join(member1);
		memberService.join(member2); 	//예외발생
		
		//then 
		fail("예외가 발생했습니다!!!!!");
		
	}

}
