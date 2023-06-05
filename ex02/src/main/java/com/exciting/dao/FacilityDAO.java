package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.FacilityDTO;

@Repository
public class FacilityDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<FacilityDTO> facilityListMap(Map<String, Object> map){
		return sqlSessionTemplate.selectList("exciting.facilityListMap", map);
	}
	
	public List<FacilityDTO> facilityList(int amuse_id){
		return sqlSessionTemplate.selectList("exciting.facilityList", amuse_id);
	}
	
	public FacilityDTO facilityDetail(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("exciting.facilityDetail", map);
	}
}
