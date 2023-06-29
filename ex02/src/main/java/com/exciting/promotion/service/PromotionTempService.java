package com.exciting.promotion.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.PromotionDTO;

public interface PromotionTempService {
	public List<PromotionDTO> selectPromotion(Map<String, Object> map);
	public List<Map<String, Object>> selectPromotionprice(Map<String, Object> map);
	public int insertOrder(Map<String, Object> map);
	public int insertOrderDetail(List<Map<String,Object>> list);
	public List<Map<String, Object>> selectOrderlist(Map<String, Object>map);
	public List<Map<String, Object>> selectgogo(Map<String, Object>map);
	public List<Map<String, Object>> selectmypoint(Map<String, Object>map);
	public List<Map<String, Object>> selectmypoint1(Map<String, Object>map);
	public int refundOrder(Map<String, Object> map);
	public int checkOrder(Map<String, Object> map);
	public int insertMypoint(Map<String, Object> map);
}

