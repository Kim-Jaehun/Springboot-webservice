package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.domain.Member;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class OrderService {
	
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	ItemRepository itemRepository;
	
	
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		
		//��ƼƼ ��ȸ
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		//������� ����
		
		
		//�ֹ���ǰ ����
		//�ֹ� ����
		//�ֹ� ����
		
		return null;
	}
}
