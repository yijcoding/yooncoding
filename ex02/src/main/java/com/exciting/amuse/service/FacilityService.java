package com.exciting.amuse.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.FacilityDTO;

public interface FacilityService {
	
	public List<FacilityDTO> facilityListMap(Map<String, Object> map);
	public List<FacilityDTO> facilityList(int amuse_id);
	public FacilityDTO facilityDetail(Map<String, Object> map);
}
