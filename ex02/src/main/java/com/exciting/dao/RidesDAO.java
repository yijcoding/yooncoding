package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.RidesDTO;

@Repository
public class RidesDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<RidesDTO> selectRidesListMap(Map<String, Object> map){
		return sqlSessionTemplate.selectList("exciting.selectRidesList");
	}
	
	public List<RidesDTO> selectRidesList(){
		return sqlSessionTemplate.selectList("exciting.selectRidesList");
	}
	
	public int ridesTotalCount(Map<String, Object> map){
		return sqlSessionTemplate.selectOne("exciting.ridesTotalCount");
	}
	
	public List<RidesDTO> ridesList(Map<String, Object> map){
		return sqlSessionTemplate.selectList("exciting.ridesList", map);
	}
	
	public RidesDTO getOneRidesDTO(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("exciting.getOneRidesDTO", map);
	}
	
	public List<RidesDTO> selectRidesList2(Map<String,Object> map){
		return sqlSessionTemplate.selectList("exciting.selectRidesList2", map);
	}
}
