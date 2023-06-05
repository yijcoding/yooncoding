package com.exciting.index.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.AmusementDAO;
import com.exciting.dto.AmusementAimageDTO;
import com.exciting.dto.AmusementDTO;


@Service
public class AmusementServiceImpl implements AmusementService {

	@Autowired
	AmusementDAO dao;
	
	public AmusementServiceImpl() {
		System.out.println("@Service 스프링 자동생성");
	}
	
	@Override
	public List<AmusementAimageDTO> selectList(Map<String, Object> map) {
		return dao.selectList(map);
	}
	
	@Override
	public List<AmusementAimageDTO> selectListAmuseInfoKorea() {
		return dao.selectListAmuseInfoKorea();
	}
	
	@Override
	public List<AmusementAimageDTO> selectListAmuseInfoForeign() {
		return dao.selectListAmuseInfoForeign();
	}
	
	@Override
	public int selectTotalCount(Map<String, Object> map) {
		return dao.selectTotalCount(map);
	}
	
	//추가(일준)
	@Override
	public AmusementDTO getOneAmuseDTO(Map<String, Object> map) {
		return dao.getOneAmuseDTO(map);
	}
	@Override
	public List<AmusementDTO> inAmusementList() {
		return dao.inAmusementList();
	}
	@Override
	public List<AmusementDTO> outAmusementList() {
		return dao.outAmusementList();
	}
	
	@Override
	public List<AmusementDTO> inAmusementListAvg() {
		return dao.inAmusementListAvg();
	}
	@Override
	public List<AmusementDTO> inAmusementListReview() {
		return dao.inAmusementListReview();
	}
	@Override
	public List<AmusementDTO> outAmusementListAvg() {
		return dao.outAmusementListAvg();
	}
	@Override
	public List<AmusementDTO> outAmusementListReview() {
		return dao.outAmusementListReview();
	}
	
	//조회수 증가
	@Override
	public void updateAmuseView(Map<String, Object> map) {
		dao.updateAmuseView(map);
	}
	@Override
	public List<AmusementDTO> outSpecialAmuseList() {
		return dao.outSpecialAmuseList();
	}
}















