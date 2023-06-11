package com.pos.web.service;

import com.pos.web.dao.MemberDao;
import com.pos.web.entity.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Member login(String id, String password) throws Exception {
		Member member = memberDao.selectById(id);
		if(member == null || !password.equals(member.getPassword())) {
			throw new Exception("DismatchInfo");
		}
		return member;
	}

	public void regist(String id, String pwd, String role) throws Exception {
		Member member = memberDao.selectById(id);
		if(member != null) {
			throw new Exception("DuplicateMemberException");
		}
		Member newMember = new Member(id, pwd, role);
		memberDao.insert(newMember);
		
	}

	
}
