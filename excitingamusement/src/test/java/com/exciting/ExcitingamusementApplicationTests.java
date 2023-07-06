package com.exciting;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exciting.entities.AmusementEntity;
import com.exciting.index.repository.IndexRepository;
import com.exciting.list.repository.ListRepository;
import com.exciting.selected.repository.SelectedRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Transactional
@SpringBootTest
class ExcitingamusementApplicationTests {
	
	@Autowired
	SelectedRepository selectedRepository;
	
	@Autowired
	ListRepository listRepository;
	
	@Autowired
	IndexRepository indexRepository;

	@Test
	void contextLoads() {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", "hong1");
		map.put("selected_id", 10);
//		map.put("pageNum", 1);
//		map.put("start", 0);
//		map.put("end", 12);
		
//		selectedRepository.count(map);
		System.out.println(indexRepository.selectListAmuseInfoKoreaDSL()); 
		System.out.println(indexRepository.selectListAmuseInfoForeignDSL()); 
	}

}
