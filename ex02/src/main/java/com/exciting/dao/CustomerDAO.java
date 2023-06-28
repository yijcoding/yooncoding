package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {
	
	@Autowired
	SqlSessionTemplate sql;
	
	public int insertAnnouncement(Map<String,Object> map) {
		System.out.println("나는야 DAO++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
		return sql.insert("customer.insertAnnouncement",map);
	}
	
	public int customerImg(Map<String,Object> map) {
		return sql.insert("customer.customerImg",map);
	}
	
	public int customerImgDelete(Map<String,Object> map) {
		return sql.delete("customer.customerImgDelete",map);
	}
	
	public int updateannouncement(Map<String,Object> map) {
		return sql.update("customer.updateannouncement",map);
	}
	
	public int faqWrite(Map<String,Object> map) {
		return sql.insert("customer.faqWrite",map);
	}
	
	public int faqupdate(Map<String,Object> map) {
		return sql.update("customer.faqupdate",map);
	}
	
	public int faqDelete(Map<String,Object> map) {
		return sql.delete("customer.faqDelete",map);
	}
	
	public int insertAnnouncementInquiry(Map<String,Object> map) {
		return sql.insert("customer.insertAnnouncementInquiry",map);
	}
	
	public int insertConsultation(Map<String,Object> map) {
		return sql.insert("customer.insertConsultation",map);
	}
	
	public int updateRefInquiry(Map<String,Object> map) {
		return sql.update("customer.updateRefInquiry",map);
	}
	
	public int updateTitleInquiry(Map<String,Object> map) {
		return sql.update("customer.updateTitleInquiry",map);
	}
	
	public int deleteconsultationView(Map<String,Object> map) {
		return sql.delete("customer.deleteconsultationView",map);
	}
	
	public int deleteAnnouncement(Map<String,Object> map) {
		return sql.delete("customer.deleteAnnouncement",map);
	}
	
	
	public Map<String, Object> selectAnnouncementCnt(Map<String,Object> map) {
		return sql.selectOne("customer.selectAnnouncementCnt",map);
	}
	
	public Map<String,Object> selectAnnouncement(Map<String,Object> map) {
		return sql.selectOne("customer.selectAnnouncement",map);
	}
	
	public Map<String,Object> selectconsultationDetailsCnt(Map<String,Object> map) {
		return sql.selectOne("customer.selectconsultationDetailsCnt",map);
	}
	
	public Map<String,Object> consultationView(Map<String,Object> map) {
		return sql.selectOne("customer.consultationView",map);
	}
	
	public List<Map<String,Object>> selectAnnouncementList(Map<String,Object> map) {
		return sql.selectList("customer.selectAnnouncementList",map);
	}
	
	public List<Map<String,Object>> customerImgSelect(Map<String,Object> map) {
		return sql.selectList("customer.customerImgSelect",map);
	}
	
	public List<Map<String,Object>> getFaqList(Map<String,Object> map) {
		return sql.selectList("customer.getFaqList",map);
	}
	
	public List<Map<String,Object>> consultationDetails(Map<String,Object> map) {
		return sql.selectList("customer.consultationDetails",map);
	}
	
	public List<Map<String,Object>> selectCustomImg(Map<String,Object> map) {
		return sql.selectList("customer.selectCustomImg",map);
	}
}
