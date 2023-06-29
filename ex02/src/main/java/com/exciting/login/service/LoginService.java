package com.exciting.login.service;

import com.exciting.dto.MemberDTO;

public interface LoginService {
	
	public int insertMember(MemberDTO dto);
	
	public int updateMember(MemberDTO dto);
	
	public MemberDTO selectMember(MemberDTO dto);
	
	public int idCheck(MemberDTO dto);
	
	public int loginCheck(MemberDTO dto);
	
	public int deleteMember(MemberDTO dto);
	
	public int updateSha(MemberDTO dto);
	
	public int shaCheck(MemberDTO dto);
	
	public MemberDTO adminCheck(MemberDTO dto);
	
	public int updatePass(MemberDTO dto);
	
	public int updateMemberImage(MemberDTO dto);
	
	public MemberDTO selectMemberImage(MemberDTO dto);
	
	public String kakaoIdCheck(MemberDTO dto);

}
