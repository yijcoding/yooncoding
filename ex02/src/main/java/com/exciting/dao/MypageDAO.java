package com.exciting.dao;


import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.Criteria;
import com.exciting.dto.MypageDTO;

@Repository
public class MypageDAO {
	
//	root-context.xml <bean>에 설정한 class를 자동으로 인식해서 생성해준다.
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate ;
	
	
	public MypageDAO() {
		System.out.println("@Repository 스프링 자동생성");
	}
	
	public List<Map<String, Object>> selectMeber(Map<String, Object> map){
		return sqlSessionTemplate.selectList("mypageMapper.selectMember",map);
	}
	
	public List<MypageDTO> selectBoard(Criteria cri) {
		System.out.println("selectBoard/Criteria : "+ cri);
		return sqlSessionTemplate.selectList("mypageMapper.selectBoard", cri);
	}
	
	public int countBoard(Criteria cri) {
		System.out.println("countBoard/Criteria : "+ cri);
		return sqlSessionTemplate.selectOne("mypageMapper.countBoard", cri);
	}
	
	
	public int countUnionAll(Criteria cri) {
		System.out.println("countUnionAll/Criteria : "+ cri);
		return sqlSessionTemplate.selectOne("mypageMapper.countUnionAll",cri);
	}
	
	public List<MypageDTO> selectUnionAll(Criteria cri) {
		System.out.println("selectUnionAll/cri : "+ cri);
		return sqlSessionTemplate.selectList("mypageMapper.selectUnionAll",cri);
	}
	
	public List<MypageDTO> selectUnionAllAsc(Criteria cri) {
		System.out.println("selectUnionAllAsc/cri : "+ cri);
		return sqlSessionTemplate.selectList("mypageMapper.selectUnionAllAsc",cri);
	}
	
	
}
