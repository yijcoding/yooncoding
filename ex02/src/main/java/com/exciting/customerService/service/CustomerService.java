package com.exciting.customerService.service;

import java.util.List;
import java.util.Map;

public interface CustomerService {
	public int insertAnnouncement(Map<String,Object> map);
	public int customerImg(Map<String,Object> map);
	public int customerImgDelete(Map<String,Object> map);
	public int updateannouncement(Map<String,Object> map);
	public int faqWrite(Map<String,Object> map);
	public int faqupdate(Map<String,Object> map);
	public int faqDelete(Map<String,Object> map);
	public int insertAnnouncementInquiry(Map<String,Object> map);
	public int updateRefInquiry(Map<String,Object> map);
	public int insertConsultation(Map<String,Object> map);
	public int updateTitleInquiry(Map<String,Object> map);
	public int deleteconsultationView(Map<String,Object> map);
	public int deleteAnnouncement(Map<String,Object> map);
	public Map<String, Object> selectAnnouncementCnt(Map<String,Object> map);
	public Map<String,Object> selectAnnouncement(Map<String,Object> map);
	public Map<String,Object> selectconsultationDetailsCnt(Map<String,Object> map);
	public Map<String,Object> consultationView(Map<String,Object> map);
	public List<Map<String,Object>> selectAnnouncementList(Map<String,Object> map);
	public List<Map<String,Object>> customerImgSelect(Map<String,Object> map);
	public List<Map<String,Object>> getFaqList(Map<String,Object> map);
	public List<Map<String,Object>> consultationDetails(Map<String,Object> map);
	public List<Map<String,Object>> selectCustomImg(Map<String,Object> map);

}
