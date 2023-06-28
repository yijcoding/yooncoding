package com.exciting.index.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.dto.AmusementAimageDTO;
import com.exciting.dto.PromotionDTO;
import com.exciting.index.service.AmusementService;

import com.exciting.promotion.service.PromotionService;
import com.exciting.selected.service.SelectedService;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Controller
@Data
@Log4j2
public class IndexController {
	
	@Autowired
	AmusementService amusementService;
	@Autowired
	SelectedService selectedService; 
	@Autowired
	PromotionService promotionService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
//		member_id값 세션에서 가져옴
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		String m_admin = String.valueOf(session.getAttribute("m_admin"));
		List<AmusementAimageDTO> koreaData = amusementService.selectListAmuseInfoKorea();
		List<AmusementAimageDTO> foreignData = amusementService.selectListAmuseInfoForeign();
		List<PromotionDTO> promotionData =  promotionService.selectPromotionList();
		mav.setViewName("index");
		mav.addObject("koreaData", koreaData);
		mav.addObject("foreignData", foreignData);
		mav.addObject("promotionData", promotionData);
		if(!(member_id == null || member_id.equals(""))) {
			mav.addObject("member_id", member_id);
			mav.addObject("m_admin", m_admin);
		}
		return mav;
	}
	
}
