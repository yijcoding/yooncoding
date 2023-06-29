package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.AmusementAimageDTO;
import com.exciting.dto.SelectedAmusementAimageDTO;
import com.exciting.dto.SelectedDTO;

@Repository
public class SelectedDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	SelectedDTO selectedDTO = new SelectedDTO();
	
	public int insert(SelectedDTO dto) {
		return sqlSessionTemplate.insert("selected.insert", dto);
	}
	
	public int delete(SelectedDTO dto) {
		return sqlSessionTemplate.delete("selected.delete", dto);
	}
	
	public int check(SelectedDTO dto) {
		return sqlSessionTemplate.selectOne("selected.check", dto);
	}
	
	public List<AmusementAimageDTO> select(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("selected.select", map);
	}
	
	public int count(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("selected.count", map);
	}
	
	public List<SelectedAmusementAimageDTO> selectTest(Map<String, Object> map){
		return sqlSessionTemplate.selectList("selected.select_test", map);
	}
	
	public int countTest(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("selected.count_test", map);
	}
}
