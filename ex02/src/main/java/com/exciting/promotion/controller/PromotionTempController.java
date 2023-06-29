package com.exciting.promotion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.dto.PromotionDTO;
import com.exciting.promotion.service.PromotionTempService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class PromotionTempController {
	@Autowired
	PromotionTempService promotionTempService;

	@GetMapping(value = "/promotion")
	public ModelAndView promotionList(@RequestParam Map<String, Object> map) {
		List<PromotionDTO> list = this.promotionTempService.selectPromotion(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data",list);
		mav.setViewName("promotion");

		return mav;
	}

	@GetMapping(value = "/promotionprice")
	public ModelAndView promotionprice(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> price = this.promotionTempService.selectPromotionprice(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", price);
		mav.setViewName("promotionprice");
		return mav;
	}

	@GetMapping(value = "/order")
	public ModelAndView orderGet(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> price = this.promotionTempService.selectPromotionprice(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", price);
		mav.setViewName("order");
		return mav;
	}

	@ResponseBody
	@PostMapping(value = "/order")
	public int orderPost(@RequestParam Map<String, Object> map, HttpSession session) {
		//		ModelAndView mav = new ModelAndView();
		//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//		Map<String, Object> mapT1 = new HashMap<String, Object>();
		//		Map<String, Object> mapT2 = new HashMap<String, Object>();
		//		Map<String, Object> mapT3 = new HashMap<String, Object>();
		//		
		//		Map<String, Object> mapT = new HashMap<String, Object>();
		//		mapT.put("member_id", "kim");
		//		promotionTempService.insertOrder(mapT);
		//		//Object obj = mapT.get("order_id");
		//		//int order_id = Integer.valueOf(obj.toString()).intValue();
		//		int order_id = Integer.valueOf(mapT.get("order_id").toString()).intValue();
		//
		////		mapT1.put("order_id", 1);
		//		mapT1.put("ticket_id", 1);
		//		mapT1.put("quantity", 4);
		//		list.add(mapT1);
		////		mapT2.put("order_id", 1);
		//		mapT2.put("ticket_id", 2);
		//		mapT2.put("quantity", 5);
		//		list.add(mapT2);
		////		mapT3.put("order_id", 1);
		//		mapT3.put("ticket_id", 3);
		//		mapT3.put("quantity", 6);
		//		list.add(mapT3);
		//		
		//		System.out.println(list);
		//		
		//		for(int i=0; i < list.size(); i++) {
		//			Map<String, Object> changeMap = list.get(i);
		//			changeMap.put("order_id", order_id);
		//		}
		//		
		//		System.out.println(list);
		//		promotionTempService.insertOrderDetail(list);
		//		mav.setViewName("promotion");
		String jsonStr = (String)map.get("myJSON");
		// ObjectMapper 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> list = null;
		// List로 변환
		try{
			list = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});
		}catch(Exception e) {
		}
		Map<String, Object> mapT = new HashMap<String, Object>();
		//		member_id값 세션에서 받아와서 mapT에 넣음
		String member_id = (String)session.getAttribute("member_id");
		mapT.put("member_id", member_id);
		//		order테이블 데이터 추가
		int check = promotionTempService.insertOrder(mapT);

		//		order테이블을 추가할 때 얻은 PK값을 list의 map에 각각 추가
		int order_id = Integer.valueOf(mapT.get("order_id").toString()).intValue();
		for(int i=0; i < list.size(); i++) {
			Map<String, Object> changeMap = list.get(i);
			changeMap.put("order_id", order_id);
		}
		//		order_detail테이블에 데이터 추가
		if(check == 1) {
			promotionTempService.insertOrderDetail(list);
		}

		return check;
	}


	@GetMapping(value = "/orderlist")
	public ModelAndView orderlist(HttpSession session) {
		String member_id = (String)session.getAttribute("member_id");
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("member_id", member_id);

		List<Map<String, Object>> orderlist = this.promotionTempService.selectOrderlist(dataMap);

		for(Map<String, Object> order : orderlist) {
			String date = order.get("order_date").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replace("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			order.put("order_date", postdate);
		}
		System.out.println("@@@@@@@@@@"+orderlist+"@@@@@@@@@@@@");
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", orderlist);
		mav.setViewName("orderlist");
		return mav;
	}
	@GetMapping(value = "/gogo")
	public ModelAndView gogo(HttpSession session, @RequestParam Map<String,Object> map) {
		String member_id = (String)session.getAttribute("member_id");
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("member_id", member_id);
		dataMap.put("order_id", map.get("order_id"));
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ !!!!!!!!!!!!!!!!!!!!!"+dataMap);
		List<Map<String, Object>> orderlist = this.promotionTempService.selectgogo(dataMap);
		
		for(Map<String, Object> order : orderlist) {
			String date = order.get("order_date").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replace("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			order.put("order_date", postdate);
		}
		System.out.println("@@@@@@@@@@"+orderlist+"@@@@@@@@@@@@");
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", orderlist);
		mav.setViewName("gogo");
		return mav;
	}
	
	@GetMapping(value = "/refund")
	public ModelAndView refund(HttpSession session) {
		String member_id = (String)session.getAttribute("member_id");
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("member_id", member_id);

		List<Map<String, Object>> orderlist = this.promotionTempService.selectOrderlist(dataMap);

		for(Map<String, Object> order : orderlist) {
			String date = order.get("order_date").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replace("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			order.put("order_date", postdate);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", orderlist);
		mav.setViewName("refund");
		return mav;
	}
	
	@PostMapping(value = "/refund")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> refundOrder(@RequestBody Map<String, Object> map) {
		System.out.println("@@@@@@@@@@@@@@@@@"+map+"@@@@@@@@@@@@@@@@");
	    int rs = this.promotionTempService.refundOrder(map);
	    System.out.println("@@@@@@@@@@@@@@@@@"+rs+"@@@@@@@@@@@@@@@@");

	    Map<String, Object> response = new HashMap<String, Object>();
	    if(rs == 1) {
	        response.put("success", true);
	    } else {
	        response.put("success", false);
	    }
	    
	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/check")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> check(@RequestBody Map<String, Object> map, HttpSession session) {
		String member_id = (String)session.getAttribute("member_id");
		map.put("member_id", member_id);
		System.out.println("@@@@@@@@@@@@@@@@@"+map+"@@@@@@@@@@@@@@@@");
		
		int rs = this.promotionTempService.checkOrder(map);
		System.out.println("@@@@@@@@@@@@@@@@@"+rs+"@@@@@@@@@@@@@@@@");
		
		Map<String, Object> response = new HashMap<String, Object>();
		if(rs == 1) {
			this.promotionTempService.insertMypoint(map);
			response.put("success", true);
		} else {
			response.put("success", false);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/mypoint")
	public ModelAndView mypoint(HttpSession session) {
		String member_id = (String)session.getAttribute("member_id");
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("member_id", member_id);
		

		List<Map<String, Object>> mypoint = this.promotionTempService.selectmypoint1(dataMap);
		List<Map<String, Object>> orderlist = this.promotionTempService.selectmypoint(dataMap);

		for(Map<String, Object> order : orderlist) {
			String date = order.get("order_date").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replace("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			order.put("order_date", postdate);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", orderlist);
		mav.addObject("data1", mypoint);
		mav.setViewName("mypoint");
		return mav;
	}

}
