package com.exciting.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.PromotionDTO;

@Repository
public class PromotionDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	PromotionDTO promotionDTO = new PromotionDTO();
	
	public List<PromotionDTO> selectList() {
		return sqlSessionTemplate.selectList("promotion.select_list");
	}
	
}
