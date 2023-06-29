package com.exciting.index.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.AmusementAimageDTO;
import com.exciting.dto.AmusementDTO;


public interface AmusementService {
	public List<AmusementAimageDTO> selectList(Map<String, Object> map);
	public List<AmusementAimageDTO> selectListAmuseInfoKorea();
	public List<AmusementAimageDTO> selectListAmuseInfoForeign();
	public int selectTotalCount(Map<String, Object> map); 
	
	//추가(일준)
	public AmusementDTO getOneAmuseDTO(Map<String, Object> map);
	public List<AmusementDTO> inAmusementList();
	public List<AmusementDTO> outAmusementList();
	
	public List<AmusementDTO> inAmusementListAvg();
	public List<AmusementDTO> inAmusementListReview();
	public List<AmusementDTO> outAmusementListAvg();
	public List<AmusementDTO> outAmusementListReview();
	
	//조회수 증가
	public void updateAmuseView(Map<String, Object> map);
	
	//해외이색놀이공원 목록
	public List<AmusementDTO> outSpecialAmuseList();
}
