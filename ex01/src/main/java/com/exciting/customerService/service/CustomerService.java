package com.exciting.customerService.service;

import java.util.List;
import java.util.Map;

public interface CustomerService {
	public int insertAnnouncement(Map<String,Object> map);
	public int customerImg(Map<String,Object> map);
	public int customerImgDelete(Map<String,Object> map);
	public int updateannouncement(Map<String,Object> map);
	public Map<String, Object> selectAnnouncementCnt(Map<String,Object> map);
	public Map<String,Object> selectAnnouncement(Map<String,Object> map);
	public List<Map<String,Object>> selectAnnouncementList(Map<String,Object> map);
	public List<Map<String,Object>> customerImgSelect(Map<String,Object> map);

}
