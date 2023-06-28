package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.AmuseReviewDTO;

@Repository
public class AmuseReviewDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insertAmuseReview(Map<String, Object> map) {
		return sqlSessionTemplate.insert("exciting.insertAmuseReview", map);
	}

	public List<AmuseReviewDTO> amuseReviewList(){
		return sqlSessionTemplate.selectList("exciting.amuseReviewList");
	}
	
	public List<AmuseReviewDTO> amuseReviewListInt(int amuse_id){
		return sqlSessionTemplate.selectList("exciting.amuseReviewListInt", amuse_id);
	}
	
	public AmuseReviewDTO getOneAmuseReviewDTO(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("exciting.getOneAmuseReviewDTO", map);
	}
	public AmuseReviewDTO getOneAmuseReviewDTO2(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("exciting.getOneAmuseReviewDTO2", map);
	}
	
	public List<AmuseReviewDTO> amuseReviewListMap(Map<String, Object> map){
		return sqlSessionTemplate.selectList("exciting.amuseReviewListMap", map);
	}
	//총 개수
	public int amuseReviewListCount(int amuse_id) {
		return sqlSessionTemplate.selectOne("exciting.amuseReviewListCount", amuse_id);
	}
	//총 개수(+검색)
	public int reviewSearchCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("exciting.reviewSearchCount", map);
	}
	//총 목록(+검색)
	public List<AmuseReviewDTO> reviewSearchTotalList(Map<String, Object> map){
		return sqlSessionTemplate.selectList("exciting.reviewSearchTotalList", map);
	}
	
	
	//평균 점수
	public float amuseReviewGradeAvg(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("exciting.amuseReviewGradeAvg", map);
	}
	
	//리뷰 삭제
	public void deleteReview(Map<String, Object> map) {
		sqlSessionTemplate.selectOne("exciting.deleteReview", map);
	}

	
	
	//====답글 관련====
	public int maxReviewId() {
		return sqlSessionTemplate.selectOne("exciting.maxReviewId");
	}
	public void updateRef(Map<String, Object> map) {
		sqlSessionTemplate.update("exciting.updateRef", map);
	}
	public void updateSeq(Map<String, Object> map) {
		sqlSessionTemplate.update("exciting.updateSeq", map);
	}
	
	public void insertReviewAnswer(Map<String, Object> map) {
		sqlSessionTemplate.selectOne("exciting.insertReviewAnswer", map);
	}
}













