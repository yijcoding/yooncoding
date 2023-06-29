package com.exciting.selected.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exciting.dto.SelectedAmusementAimageDTO;
import com.exciting.dto.SelectedDTO;
import com.exciting.selected.service.SelectedService;

@RestController
@RequestMapping("/selectedapi/*")
public class SelectedApiController {
	
	@Autowired
	SelectedService selectedService;
	
	@GetMapping("/selectedlist")
	public List<SelectedAmusementAimageDTO> selectedList(@RequestParam Map<String, Object> map, HttpServletRequest request) {
//		member_id값 세션에서 가져옴
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
//		selected_id와 limitCount 맵에 추가
		int limitCount = 6;
		if(!(member_id == null || member_id.equals(""))) {
			map.put("member_id", member_id);
		}
		map.put("limitCount", limitCount);
		List<SelectedAmusementAimageDTO> selectedData = selectedService.selectTest(map);
		return selectedData;
	}
	
	@PostMapping("/selectedlist")
	public List<SelectedAmusementAimageDTO> selectedListClick(@RequestParam Map<String, Object> map, HttpServletRequest request) {
//		member_id값 세션에서 가져옴
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
//		selected_id와 limitCount 맵에 추가
		int selected_id = Integer.parseInt((String)map.get("selected_id"));
		System.out.println(map.get("selected_id"));
		
		int limitCount = 6;
		if(!(member_id == null || member_id.equals(""))) {
			map.put("member_id", member_id);
		}
		map.put("selected_id", selected_id);
		map.put("limitCount", limitCount);
		List<SelectedAmusementAimageDTO> selectedData = selectedService.selectTest(map);
		
		return selectedData;
	}
	
	@PostMapping("/selectedcount")
	public int selectedListCount(@RequestParam Map<String, Object> map, HttpServletRequest request) {
//		member_id값 세션에서 가져옴
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
//		selected_id 맵에 추가
		if(map.get("selected_id") != null) {
			int selected_id = Integer.parseInt((String)map.get("selected_id"));
			map.put("selected_id", selected_id);
		}
		if(!(member_id == null || member_id.equals(""))) {
			map.put("member_id", member_id);
		}
		
		int selectCount = selectedService.countTest(map);
		
		return selectCount;
	}
	
}
