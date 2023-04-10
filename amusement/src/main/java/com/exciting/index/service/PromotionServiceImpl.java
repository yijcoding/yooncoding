package com.exciting.index.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.AmusementDAO;
import com.exciting.dao.PromotionDAO;
import com.exciting.dto.AmusementAimageDTO;
import com.exciting.dto.AmusementDTO;
import com.exciting.dto.PromotionDTO;


@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionDAO dao;
	
	public PromotionServiceImpl() {
		System.out.println("@Service 스프링 자동생성");
	}
	
	@Override
	public List<PromotionDTO> selectList() {
		return dao.selectList();
	}
	
}
