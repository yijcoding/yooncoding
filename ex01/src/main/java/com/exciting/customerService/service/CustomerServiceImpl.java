package com.exciting.customerService.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO dao;
	
	
	//공지사항 추가
	@Override
	public int insertAnnouncement(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("나는야 서비스++++++++++++++++++++++++++++++++++++++++"+map);
		return dao.insertAnnouncement(map);
	}
	
	public int customerImg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.customerImg(map);
	}


	@Override
	public Map<String,Object> selectAnnouncement(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectAnnouncement(map);
	}

	@Override
	public List<Map<String, Object>> selectAnnouncementList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectAnnouncementList(map);
	}

	@Override
	public Map<String, Object> selectAnnouncementCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectAnnouncementCnt(map);
	}

	@Override
	public List<Map<String, Object>> customerImgSelect(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.customerImgSelect(map);
	}

	@Override
	public int customerImgDelete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.customerImgDelete(map);
	}

	@Override
	public int updateannouncement(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.updateannouncement(map);
	}

	
	

}
