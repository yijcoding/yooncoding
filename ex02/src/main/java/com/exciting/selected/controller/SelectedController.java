package com.exciting.selected.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.dto.SelectedAmusementAimageDTO;
import com.exciting.selected.service.SelectedService;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Controller
@Data
@Log4j2
public class SelectedController {
	
	@Autowired
	SelectedService selectedService; 
	
	@RequestMapping(value = "/mypage/selectedmypage", method = RequestMethod.GET)
	public ModelAndView selectedTest(@RequestParam Map<String, Object> map, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
//		member_id값 세션에서 가져옴
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
//		로그인 하지 않았을 경우 로그인 페이지로 보냄, 아닐경우 맵에 추가
		if (member_id == null || member_id.equals("")) {
			mav.setViewName("redirect:/login");
			return mav;
		} else {
			map.put("member_id", "hong1");
		}

		Integer selected_id = null;
		int limitCount = 6;
		
		map.put("limitCount", limitCount);
		map.put("selected_id", selected_id);
		List<SelectedAmusementAimageDTO> selectedData = selectedService.selectTest(map);
		
		mav.setViewName("mypage/selectedmypage");
		mav.addObject("selectedData", selectedData);
		
		if(!(member_id == null || member_id.equals(""))) {
			mav.addObject("member_id", member_id);
		}
		
		return mav;
	}
	
	/*
	 * @RequestMapping(value = "/mypage/selectedmypage", method = RequestMethod.GET)
	 * public ModelAndView selected(@RequestParam Map<String, Object> map,
	 * HttpServletRequest request) { ModelAndView mav = new ModelAndView(); //
	 * member_id값 세션에서 가져옴 HttpSession session = request.getSession(); String
	 * member_id = (String) session.getAttribute("member_id"); // 로그인 하지 않았을 경우 로그인
	 * 페이지로 보냄, 아닐경우 맵에 추가 if (member_id == null || member_id.equals("")) {
	 * mav.setViewName("redirect:/login"); return mav; } else { map.put("member_id",
	 * member_id); } // 페이징 String pageTemp = (String)map.get("pageNum"); int
	 * pageNum = 1; if(pageTemp != null && !(pageTemp.equals(""))){ pageNum =
	 * Integer.parseInt(pageTemp); }
	 * 
	 * int pageSize = 6; int blockPage = 5; int totalCount =
	 * selectedService.count(map); int totalPage =
	 * (int)Math.ceil((double)totalCount/pageSize); int start = (pageNum - 1) *
	 * pageSize; int end = pageSize;
	 * 
	 * map.put("start", start); map.put("end", end); List<AmusementAimageDTO>
	 * selectedData = selectedService.select(map);
	 * 
	 * String searchName = (String)map.get("searchName"); String searchCountry =
	 * (String)map.get("searchCountry"); String order = (String)map.get("order"); //
	 * 검색값 오류나서 null대신 빈값 집어넣음 if(searchName == null) { searchName = ""; }
	 * if(searchCountry == null) { searchCountry = ""; } if(order == null) { order =
	 * ""; }
	 * 
	 * String listPage = ListPage.pagingStr(totalCount, pageSize, blockPage,
	 * pageNum, searchName, searchCountry, order, request.getRequestURI());
	 * 
	 * mav.setViewName("mypage/selectedmypage"); mav.addObject("selectedData",
	 * selectedData); mav.addObject("listPage", listPage);
	 * mav.addObject("searchCountry", searchCountry); mav.addObject("order", order);
	 * 
	 * if(!(member_id == null || member_id.equals(""))) { mav.addObject("member_id",
	 * member_id); }
	 * 
	 * return mav; }
	 */
	

	
	
	
}
