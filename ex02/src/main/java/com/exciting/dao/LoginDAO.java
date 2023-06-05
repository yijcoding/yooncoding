package com.exciting.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.MemberDTO;

@Repository
public class LoginDAO {
	
//	root-context.xml <bean>에 설정한 class를 자동으로 인식해서 생성해준다.
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate ;
	
	
	public LoginDAO() {
		System.out.println("@Repository 스프링 자동생성");
	}
	
	public int insertMember(MemberDTO dto) {
		System.out.println("memberDAO.insertMember(dto) 접근");
		return sqlSessionTemplate.insert("loginMapper.insertMember",dto);
	}
	
	public int idCheck(MemberDTO dto) {
		System.out.println("memberDAO.idCheck(dto) 접근");
		return sqlSessionTemplate.selectOne("loginMapper.idCheck",dto);
	}
	
	public int loginCheck(MemberDTO dto) {
		System.out.println("memberDAO.loginCheck(dto) 접근");
		return sqlSessionTemplate.selectOne("loginMapper.loginCheck",dto);
	}
	
	public int deleteMember(MemberDTO dto) {
		return sqlSessionTemplate.delete("loginMapper.deleteMember",dto);
	}
	
	public int updateSha(MemberDTO dto) {
		return sqlSessionTemplate.update("loginMapper.updateSha",dto);
	}
	
	public int shaCheck(MemberDTO dto) {
		return sqlSessionTemplate.selectOne("loginMapper.shaCheck",dto);
	}
	
	public MemberDTO selectMember(MemberDTO dto) {
		return sqlSessionTemplate.selectOne("loginMapper.selectMember",dto);
	}
	
	public int updatePass(MemberDTO dto) {
		System.out.println("memberDAO.updatePass(dto) 접근");
		return sqlSessionTemplate.update("loginMapper.updatePass",dto);
	}
	
	public MemberDTO adminCheck(MemberDTO dto) {
		System.out.println("memberDAO.updatePass(dto) 접근");
		return sqlSessionTemplate.selectOne("loginMapper.adminCheck",dto);
	}
	
	public MemberDTO selectMemberImage(MemberDTO dto) {
		return sqlSessionTemplate.selectOne("loginMapper.selectMemberImage",dto);
	}
	
	public int updateMemberImage(MemberDTO dto) {
		return sqlSessionTemplate.update("loginMapper.updateMemberImage",dto);
	}
	
	public int updateMember(MemberDTO dto) {
		System.out.println("memberDAO.updateMember(dto) 접근");
		return sqlSessionTemplate.update("loginMapper.updateMember",dto);
	}

	public String kakaoIdCheck(MemberDTO dto) {
		System.out.println("memberDAO.kakaoIdCheck(dto) 접근");
		return sqlSessionTemplate.selectOne("loginMapper.kakaoIdCheck",dto);
	}
}
