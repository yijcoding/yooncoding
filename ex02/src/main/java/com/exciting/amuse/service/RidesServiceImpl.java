package com.exciting.amuse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.RidesDAO;
import com.exciting.dto.RidesDTO;

@Service
public class RidesServiceImpl implements RidesService{
	
	@Autowired
	RidesDAO rDao;
	
	@Override
	public List<RidesDTO> selectRidesListMap(Map<String, Object> map) {
		return rDao.selectRidesListMap(map);
	}
	
	@Override
	public List<RidesDTO> selectRidesList() {
		return rDao.selectRidesList();
	}
	
	@Override
	public RidesDTO getOneRidesDTO(Map<String, Object> map) {
		return rDao.getOneRidesDTO(map);
	}
	
	@Override
	public List<RidesDTO> selectRidesList2(Map<String, Object> map) {
		return rDao.selectRidesList2(map);
	}
	
	@Override
	public List<RidesDTO> ridesList(Map<String, Object> map) {
		return rDao.ridesList(map);
	}
	
	@Override
	public int ridesTotalCount(Map<String, Object> map) {
		return rDao.ridesTotalCount(map);
	}
}

