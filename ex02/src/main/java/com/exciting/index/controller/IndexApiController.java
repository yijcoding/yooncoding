package com.exciting.index.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.CookieGenerator;

import com.exciting.dto.SelectedDTO;
import com.exciting.selected.service.SelectedService;


@RestController
@RequestMapping("/indexapi/*")
public class IndexApiController {
	
	@Autowired
	SelectedService selectedService;
	
	@PostMapping("/check")
	public Map<String, Object> check(SelectedDTO dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		int check = selectedService.check(dto);
		map.put("check", check);
		return map;
	}
	
	@PostMapping("/insertDelete")
	public Map<String, Object> insertDelete(SelectedDTO dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		int check = selectedService.check(dto);
		if(check == 1) {
			selectedService.delete(dto);
			check = 0;
		} else {
			selectedService.insert(dto);
			check = 1;
		}
		map.put("check", check);
		return map;
	}
	
	@PostMapping("/PopupCookie")
	public String popupCookie(@RequestParam Map<String,Object> map, HttpServletRequest request, HttpServletResponse response) {
		String chkVal = (String)map.get("inactiveToday");
		if (chkVal != null && chkVal.equals("1")) {
		    Cookie cookie = new Cookie("PopupClose", "off");  // 쿠키 생성 
		    cookie.setPath("/");  // 경로 설정
		    cookie.setMaxAge(60 * 60 * 24);  // 유지 기간 설정
		    response.addCookie(cookie);  // 응답 객체에 추가 
		}
	   
	    return "chkVal";
	}
	
}
