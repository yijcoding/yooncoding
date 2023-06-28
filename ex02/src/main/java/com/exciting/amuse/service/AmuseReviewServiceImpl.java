package com.exciting.amuse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.AmuseReviewDAO;
import com.exciting.dto.AmuseReviewDTO;

@Service
public class AmuseReviewServiceImpl implements AmuseReviewService{
	
	@Autowired
	AmuseReviewDAO arDao;
	
	@Override
	public int insertAmuseReview(Map<String, Object> map) {
		return arDao.insertAmuseReview(map);
	}
	
	@Override
	public List<AmuseReviewDTO> amuseReviewList() {
		return arDao.amuseReviewList();
	}
	
	@Override
	public List<AmuseReviewDTO> amuseReviewListInt(int amuse_id) {
		return arDao.amuseReviewListInt(amuse_id);
	}
	
	@Override
	public AmuseReviewDTO getOneAmuseReviewDTO(Map<String, Object> map) {
		return arDao.getOneAmuseReviewDTO(map);
	}
	@Override
	public AmuseReviewDTO getOneAmuseReviewDTO2(Map<String, Object> map) {
		return arDao.getOneAmuseReviewDTO2(map);
	}
	
	@Override
	public List<AmuseReviewDTO> amuseReviewListMap(Map<String, Object> map) {
		return arDao.amuseReviewListMap(map);
	}
	
	@Override
	public int amuseReviewListCount(int amuse_id) {
		return arDao.amuseReviewListCount(amuse_id);
	}
	@Override
	public int reviewSearchCount(Map<String, Object> map) {
		return arDao.reviewSearchCount(map);
	}
	@Override
	public List<AmuseReviewDTO> reviewSearchTotalList(Map<String, Object> map) {
		return arDao.reviewSearchTotalList(map);
	}
	
	@Override
	public float amuseReviewGradeAvg(Map<String, Object> map) {
		return arDao.amuseReviewGradeAvg(map);
	}
	
	@Override
	public void deleteReview(Map<String, Object> map) {
		arDao.deleteReview(map);
	}
	
	//===리뷰 답글 관련
	@Override
	public int maxReviewId() {
		return arDao.maxReviewId();
	}
	@Override
	public void updateRef(Map<String, Object> map) {
		arDao.updateRef(map);
	}
	@Override
	public void updateSeq(Map<String, Object> map) {
		arDao.updateSeq(map);
	}
	@Override
	public void insertReviewAnswer(Map<String, Object> map) {
		arDao.insertReviewAnswer(map);
	}
}















