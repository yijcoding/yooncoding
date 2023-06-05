package com.exciting.amuse.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.AmuseReviewDTO;

public interface AmuseReviewService {
	
	public int insertAmuseReview(Map<String, Object> map);
	
	public List<AmuseReviewDTO> amuseReviewList();
	
	public List<AmuseReviewDTO> amuseReviewListInt(int amuse_id);
	
	public AmuseReviewDTO getOneAmuseReviewDTO(Map<String, Object> map);
	public AmuseReviewDTO getOneAmuseReviewDTO2(Map<String, Object> map);
	
	public List<AmuseReviewDTO> amuseReviewListMap(Map<String, Object> map);
	
	public int amuseReviewListCount(int amuse_id);
	public int reviewSearchCount(Map<String, Object> map);
	public List<AmuseReviewDTO> reviewSearchTotalList(Map<String, Object> map);
	
	public float amuseReviewGradeAvg(Map<String, Object> map);
	
	public void deleteReview(Map<String, Object> map);
	
	//===리뷰 답글 관련===
	public int maxReviewId();
	public void updateRef(Map<String, Object> map);
	public void updateSeq(Map<String, Object> map);
	public void insertReviewAnswer(Map<String, Object> map);
}
