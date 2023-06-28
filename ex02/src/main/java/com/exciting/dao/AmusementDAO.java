package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.AimageDTO;
import com.exciting.dto.AmusementAimageDTO;
import com.exciting.dto.AmusementDTO;

@Repository
public class AmusementDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
//	AmusementDTO amusementDTO = new AmusementDTO();
//	AmusementAimageDTO amusementAimageDTO = new AmusementAimageDTO();
	
	
	public AmusementDAO() {
		System.out.println("@Repository 스프링 자동생성");
	}
	
	public List<AmusementAimageDTO> selectList(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("amusement.select_list", map);
	}
	
	public List<AmusementAimageDTO> selectListAmuseInfoKorea() {
		return sqlSessionTemplate.selectList("amusement.select_list_amuse_info_korea");
	}
	
	public List<AmusementAimageDTO> selectListAmuseInfoForeign() {
		return sqlSessionTemplate.selectList("amusement.select_list_amuse_info_foreign");
	}
	
	public int selectTotalCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("amusement.select_total_count", map);
	}
	
	// 추가(일준)
	public AmusementDTO getOneAmuseDTO(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("exciting.getOneAmusementDTO", map);
	}
	public List<AimageDTO> aImgList(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("exciting.amuseImgList", map);
	}
	public List<AmusementDTO> inAmusementList(){
		return sqlSessionTemplate.selectList("exciting.inAmusementList");
	}
	public List<AmusementDTO> outAmusementList(){
		return sqlSessionTemplate.selectList("exciting.outAmusementList");
	}
	//국내외 리뷰 많은 순 & 평점 높은 순
	public List<AmusementDTO> inAmusementListAvg(){
		return sqlSessionTemplate.selectList("exciting.inAmusementListAvg");
	}
	public List<AmusementDTO> inAmusementListReview(){
		return sqlSessionTemplate.selectList("exciting.inAmusementListReview");
	}
	public List<AmusementDTO> outAmusementListAvg(){
		return sqlSessionTemplate.selectList("exciting.outAmusementListAvg");
	}
	public List<AmusementDTO> outAmusementListReview(){
		return sqlSessionTemplate.selectList("exciting.outAmusementListReview");
	}
	
	//조회수 증가
	public void updateAmuseView(Map<String, Object> map) {
		sqlSessionTemplate.update("exciting.updateAmuseView", map);
	}
	//해외이색놀이공원 목록
	public List<AmusementDTO> outSpecialAmuseList(){
		return sqlSessionTemplate.selectList("exciting.outSpecialAmuseList");
	}
}














