package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// ȸ�� ����
	@Transactional
	public Long join(Member member) {
		vaildateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	// ȸ�� �ߺ� ���� ����
	private void vaildateDuplicateMember(Member member) {
		List<Member> findMember = memberRepository.findByName(member.getName());
		if(!findMember.isEmpty()) {
			throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
		} 
	}

	// ȸ�� ��ü ��ȸ
	public List<Member> findMembers(){
		return memberRepository.findAll(); 
	}
	
	// ȸ�� ��ü ��ȸ
	public Member findOne(long memberId){
		return memberRepository.findOne(memberId);
	}
	
}
