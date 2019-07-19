package com.yem.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yem.dto.YemMemberDTO;
import com.yem.entity.YemMember;
import com.yem.member.dao.YemMemberMapper;

@Service
public class MemberService {

	@Autowired
	private YemMemberMapper yemMemberMapper;
	
	public YemMember getMember(Long memberCode) {
		return yemMemberMapper.selectByMemberCode(memberCode);
	}
	
	public List<YemMember> getMemberList(YemMemberDTO member) {
		return yemMemberMapper.selectMemberList(member);
	}
	
	public int getMemberListCount(YemMemberDTO member) {
		return yemMemberMapper.selectMemberListCount(member);
	}
	
	public boolean modifyMember(YemMember member) {
		return yemMemberMapper.updateByMemberCodeSelective(member) > 0 ? true : false;
	}
	
	public boolean addMember(YemMember member) {
		return yemMemberMapper.insertSelective(member) > 0 ? true : false;
	}
	
}
