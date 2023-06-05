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
	@Override
	public int customerImg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.customerImg(map);
	}
	@Override
	public int faqupdate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.faqupdate(map);
	}
	@Override
	public int updateRefInquiry(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.updateRefInquiry(map);
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
	public List<Map<String, Object>> consultationDetails(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.consultationDetails(map);
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

	@Override
	public List<Map<String, Object>> getFaqList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.getFaqList(map);
	}

	@Override
	public int faqWrite(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.faqWrite(map);
	}

	@Override
	public int faqDelete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.faqDelete(map);
	}

	@Override
	public int insertAnnouncementInquiry(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertAnnouncementInquiry(map);
	}

	@Override
	public Map<String, Object> selectconsultationDetailsCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectconsultationDetailsCnt(map);
	}

	@Override
	public Map<String, Object> consultationView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.consultationView(map);
	}
	@Override
	public int insertConsultation(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertConsultation(map);
	}
	@Override
	public int updateTitleInquiry(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.updateTitleInquiry(map);
	}
	@Override
	public List<Map<String, Object>> selectCustomImg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectCustomImg(map);
	}
	@Override
	public int deleteconsultationView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.deleteconsultationView(map);
	}
	@Override
	public int deleteAnnouncement(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.deleteAnnouncement(map);
	}
	
	
	
	

}
