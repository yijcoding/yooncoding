package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.PromotionDTO;



@Repository
public class PromotionTempDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<PromotionDTO> selectPromotion(Map<String, Object>map) {
		return sqlSessionTemplate.selectList("promotionTemp.select_promotion", map);
	}
	
	public List<Map<String, Object>> selectPromotionprice(Map<String, Object> map){
		return sqlSessionTemplate.selectList("promotionTemp.select_promotionprice", map);
	}
	
	public int insertOrder(Map<String, Object> map) {
		return sqlSessionTemplate.insert("promotionTemp.insert_order", map);
	}
	
	public int insertOrderDetail(List<Map<String,Object>> list) {
		return sqlSessionTemplate.insert("promotionTemp.insert_order_detail", list);
	}
	
	public List<Map<String, Object>> selectOrderlist(Map<String, Object>map) {
		return sqlSessionTemplate.selectList("promotionTemp.select_orderlist", map);
	}
	public List<Map<String, Object>> selectMypoint(Map<String, Object>map) {
		return sqlSessionTemplate.selectList("promotionTemp.select_mypoint", map);
	}
	public List<Map<String, Object>> selectMypoint1(Map<String, Object>map) {
		return sqlSessionTemplate.selectList("promotionTemp.select_mypoint1", map);
	}
	public List<Map<String, Object>> selectgogo(Map<String, Object>map) {
		return sqlSessionTemplate.selectList("promotionTemp.select_gogo", map);
	}
	
	public int refundOrder(Map<String, Object> map) {
		return sqlSessionTemplate.update("promotionTemp.refundOrder",map);
	}
	
	public int checkOrder(Map<String, Object> map) {
		return sqlSessionTemplate.update("promotionTemp.checkOrder",map);
	}
	
	public int insertMypoint(Map<String, Object> map) {
		return sqlSessionTemplate.insert("promotionTemp.insert_mypoint", map);
	}
	
}
