package com.exciting.mypage.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.Criteria;
import com.exciting.dto.MypageDTO;

public interface MypageService {
	
	public List<MypageDTO> selectBoard(Criteria cri);
	
	public int countBoard(Criteria cri);
	
	public int countUnionAll(Criteria cri);
	
	public List<MypageDTO> selectUnionAll(Criteria cri);
	
	public List<MypageDTO> selectUnionAllAsc(Criteria cri);
	
	public List<Map<String, Object>> selectMeber(Map<String, Object> map);
	
}
