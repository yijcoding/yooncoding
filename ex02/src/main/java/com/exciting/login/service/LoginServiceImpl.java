package com.exciting.login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.LoginDAO;
import com.exciting.dto.MemberDTO;



@Service
public class LoginServiceImpl implements LoginService{

	//  BookDao는 autowired 할수 있다. 
	//	왜냐하면 BookDao class에 @Repository를 설정해 두었기 때문이다.
	@Autowired
	LoginDAO loginDao;

	public LoginServiceImpl() {
		System.out.println("@serviceImpl 스프링 자동생성");
	}

	@Override
	public int insertMember(MemberDTO dto) {
		return loginDao.insertMember(dto);
	}
	
	@Override
	public int updateMember(MemberDTO dto) {
		return loginDao.updateMember(dto);
	}
	
	@Override
	public int idCheck(MemberDTO dto) {
		return loginDao.idCheck(dto);
	}
	
	@Override
	public int loginCheck(MemberDTO dto) {
		return loginDao.loginCheck(dto);
	}
	
	@Override
	public int deleteMember(MemberDTO dto) {
		return loginDao.deleteMember(dto);
	}
	
	@Override
	public int updateSha(MemberDTO dto) {
		return loginDao.updateSha(dto);
	}
	
	@Override
	public int shaCheck(MemberDTO dto) {
		return loginDao.shaCheck(dto);
	}
	
	@Override
	public MemberDTO selectMember(MemberDTO dto) {
		return loginDao.selectMember(dto);
	}
	
	@Override
	public int updatePass(MemberDTO dto) {
		return loginDao.updatePass(dto);
	}
	
	@Override
	public MemberDTO adminCheck(MemberDTO dto) {
		return loginDao.adminCheck(dto);
	}
	
	@Override
	public MemberDTO selectMemberImage(MemberDTO dto) {
		return loginDao.selectMemberImage(dto);
	}
	
	@Override
	public int updateMemberImage(MemberDTO dto) {
		return loginDao.updateMemberImage(dto);
	}
	
	@Override
	public String kakaoIdCheck(MemberDTO dto) {
		return loginDao.kakaoIdCheck(dto);
	}
}	
