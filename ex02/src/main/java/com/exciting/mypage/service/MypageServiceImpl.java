package com.exciting.mypage.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.MypageDAO;
import com.exciting.dto.Criteria;
import com.exciting.dto.MypageDTO;



@Service
public class MypageServiceImpl implements MypageService{

	//  BookDao는 autowired 할수 있다. 
	//	왜냐하면 BookDao class에 @Repository를 설정해 두었기 때문이다.
	@Autowired
	MypageDAO mypageDAO;

	public MypageServiceImpl() {
		System.out.println("@serviceImpl 스프링 자동생성");
	}
	
	@Override
	public List<MypageDTO> selectBoard(Criteria cri) {
		return mypageDAO.selectBoard(cri);
	}
	
	@Override
	public int countBoard(Criteria cri) {
		return mypageDAO.countBoard(cri);
	}
	
	@Override
	public int countUnionAll(Criteria cri) {
		return mypageDAO.countUnionAll(cri);
	}
	
	@Override
	public List<MypageDTO> selectUnionAll(Criteria cri) {
		return mypageDAO.selectUnionAll(cri);
	}
	
	@Override
	public List<MypageDTO> selectUnionAllAsc(Criteria cri) {
		return mypageDAO.selectUnionAllAsc(cri);
	}
	
	@Override
	public List<Map<String, Object>> selectMeber(Map<String, Object> map) {
		return mypageDAO.selectMeber(map);
	}
}	
