package com.exciting.amuse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.FacilityDAO;
import com.exciting.dto.FacilityDTO;

@Service
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	FacilityDAO fDao;
	
	@Override
	public List<FacilityDTO> facilityListMap(Map<String, Object> map) {
		return fDao.facilityListMap(map);
	}
	@Override
	public List<FacilityDTO> facilityList(int amuse_id) {
		return fDao.facilityList(amuse_id);
	}
	@Override
	public FacilityDTO facilityDetail(Map<String, Object> map) {
		return fDao.facilityDetail(map);
	}
}
