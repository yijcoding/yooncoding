package com.exciting.selected.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.AmusementAimageDTO;
import com.exciting.dto.PromotionDTO;
import com.exciting.dto.SelectedAmusementAimageDTO;
import com.exciting.dto.SelectedDTO;

public interface SelectedService {
	public int insert(SelectedDTO dto);
	public int delete(SelectedDTO dto);
	public int check(SelectedDTO dto);
	public List<AmusementAimageDTO> select(Map<String, Object> map);
	public int count(Map<String, Object> map);
	public List<SelectedAmusementAimageDTO> selectTest(Map<String, Object> map); 
	public int countTest(Map<String, Object> map);
}
