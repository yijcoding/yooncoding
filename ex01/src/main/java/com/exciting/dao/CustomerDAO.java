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
	
	public Map<String, Object> selectAnnouncementCnt(Map<String,Object> map) {
		return sql.selectOne("customer.selectAnnouncementCnt",map);
	}
	
	public Map<String,Object> selectAnnouncement(Map<String,Object> map) {
		return sql.selectOne("customer.selectAnnouncement",map);
	}
	
	public List<Map<String,Object>> selectAnnouncementList(Map<String,Object> map) {
		return sql.selectList("customer.selectAnnouncementList",map);
	}
	
	public List<Map<String,Object>> customerImgSelect(Map<String,Object> map) {
		System.out.println("나는 DAO Image Select++++++++++++++++++++++++++++++++++++++++++"+map);
		return sql.selectList("customer.customerImgSelect",map);
	}
}
