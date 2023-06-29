package com.exciting.amuse.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.RidesDTO;

public interface RidesService {
	public List<RidesDTO> selectRidesListMap(Map<String, Object> map);
	public List<RidesDTO> selectRidesList();
	
	public List<RidesDTO> selectRidesList2(Map<String, Object> map);
	
	public RidesDTO getOneRidesDTO(Map<String, Object> map);
	
	public List<RidesDTO> ridesList(Map<String, Object> map);
	
	public int ridesTotalCount(Map<String, Object> map);
}
