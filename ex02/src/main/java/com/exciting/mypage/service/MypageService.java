package com.exciting.mypage.service;

import java.util.List;

import com.exciting.dto.Criteria;
import com.exciting.dto.MypageDTO;
import com.exciting.dto.PagingDTO;

public interface MypageService {
	
	public List<MypageDTO> selectBoard(Criteria cri);
	
	public int countBoard(Criteria cri);
	
	public int countUnionAll(Criteria cri);
	
	public List<MypageDTO> selectUnionAll(Criteria cri);
	
	public List<MypageDTO> selectUnionAllAsc(Criteria cri);
	
}
