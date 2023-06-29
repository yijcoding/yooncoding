package com.exciting.promotion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.PromotionTempDAO;
import com.exciting.dto.PromotionDTO;

@Service
public class PromotionTempServiceImpl implements PromotionTempService{

	@Autowired
	PromotionTempDAO promotionTempDAO;

	@Override
	public List<PromotionDTO> selectPromotion(Map<String, Object> map) {
		return promotionTempDAO.selectPromotion(map);
	}

	@Override
	public List<Map<String, Object>> selectPromotionprice(Map<String, Object> map) {
		return promotionTempDAO.selectPromotionprice(map);
	}

	@Override
	public int insertOrder(Map<String, Object> map) {
		return promotionTempDAO.insertOrder(map);
	}

	@Override
	public int insertOrderDetail(List<Map<String, Object>> list) {
		return promotionTempDAO.insertOrderDetail(list);
	}

	@Override
	public List<Map<String, Object>> selectOrderlist(Map<String, Object> map) {
		return promotionTempDAO.selectOrderlist(map);
	}
	@Override
	public List<Map<String, Object>> selectgogo(Map<String, Object> map) {
		return promotionTempDAO.selectgogo(map);
	}
	@Override
	public List<Map<String, Object>> selectmypoint(Map<String, Object> map) {
		return promotionTempDAO.selectMypoint(map);
	}
	@Override
	public List<Map<String, Object>> selectmypoint1(Map<String, Object> map) {
		return promotionTempDAO.selectMypoint1(map);
	}

	@Override
	public int refundOrder(Map<String, Object> map) {
		return promotionTempDAO.refundOrder(map);
	}
	@Override
	public int checkOrder(Map<String, Object> map) {
		return promotionTempDAO.checkOrder(map);
	}
	
	@Override
	public int insertMypoint(Map<String, Object> map) {
		return promotionTempDAO.insertMypoint(map);
	}
}
