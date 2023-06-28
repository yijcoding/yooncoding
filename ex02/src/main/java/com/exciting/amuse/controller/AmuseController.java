package com.exciting.amuse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import com.exciting.amuse.service.AmuseImageService;
import com.exciting.amuse.service.AmuseReviewService;
import com.exciting.amuse.service.FacilityService;
import com.exciting.amuse.service.RidesService;
import com.exciting.dto.AimageDTO;
import com.exciting.dto.AmuseReviewDTO;
import com.exciting.dto.AmusementDTO;
import com.exciting.dto.FacilityDTO;
import com.exciting.dto.RidesDTO;
import com.exciting.index.service.AmusementService;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Controller
@Data
@Log4j2
public class AmuseController {
	
	static final int countPerPage = 5;
	
	@Autowired
	RidesService ridesService;
	
	@Autowired
	FacilityService facilityService;
	
	//======amuse image========
	@Autowired
	AmuseImageService amuseImageService;

	//==========amusement==========
	@Autowired
	AmusementService amusementService;
	
	@Autowired
	AmuseReviewService amuseReviewService;
	
	//====놀이공원 상세정보====
	@GetMapping("/amuseDetail")
	public ModelAndView amuseDetail(
			@RequestParam Map<String, Object> map, 
			ModelAndView mav, 
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		System.out.println("==============================================map = " + map);
		AmusementDTO dto = this.amusementService.getOneAmuseDTO(map);
		List<AimageDTO> aImgList = this.amuseImageService.aImgList(map);
		List<RidesDTO> rList = this.ridesService.ridesList(map);
		List<FacilityDTO> fList = this.facilityService.facilityListMap(map);
		//해외이색놀이공원 목록
		List<AmusementDTO> outSpecialList = this.amusementService.outSpecialAmuseList();
		
		//한 페이지에 보여줄 게시물 개수
		map.put("countPerPage", countPerPage);
		//현재 페이지 번호(디폴트)
		map.put("currentPage", 1);
		
		System.out.println("map = " + map);
		
		int pagingNum = ((int)map.get("currentPage") - 1) * (int)map.get("countPerPage");
		map.put("pagingNum", pagingNum);
		
		System.out.println("pagingNum = " + map.get("pagingNum"));
		
		List<AmuseReviewDTO> reviewList = this.amuseReviewService.reviewSearchTotalList(map);
		
		System.out.println("reviewList = " + reviewList);
		System.out.println("로그인 한 member_id = " + session.getAttribute("member_id"));

		//paging => 작성한 리뷰가 있어야 가능
		if(!reviewList.isEmpty()) {
			//1) 총 게시물 개수
			int totalRecordCount = this.amuseReviewService.reviewSearchCount(map);
			float avg = this.amuseReviewService.amuseReviewGradeAvg(map);
			
			//2) (하단에 보여질) 전체 페이지 수
			int totalPageCount = (totalRecordCount + countPerPage - 1) / countPerPage;
			//Math.ceil를 이용할 수 있음
			
			mav.addObject("totalPageCount", totalPageCount);
			mav.addObject("totalCnt", totalRecordCount);
			mav.addObject("review", reviewList);
			mav.addObject("avg", avg);
		}
		
		//국내 or 해외
		if(dto.getA_country().equals("대한민국")) {
			List<AmusementDTO> inAvgGradeList = this.amusementService.inAmusementListAvg();
			List<AmusementDTO> inReviewCntList = this.amusementService.inAmusementListReview();
			mav.addObject("inAGList", inAvgGradeList);
			mav.addObject("inRCList", inReviewCntList);
		}
		else {
			List<AmusementDTO> outAvgGradeList = this.amusementService.outAmusementListAvg();
			List<AmusementDTO> outReviewCntList = this.amusementService.outAmusementListReview();
			mav.addObject("outAGList", outAvgGradeList);
			mav.addObject("outRCList", outReviewCntList);
		}

		//============새로고침 조회수 무한 증가 방지(쿠키 이용)============
		// 비교를 위한 쿠키
		Cookie viewCookie = null;
		
		Cookie[] cookies = request.getCookies();
		
		// 쿠키가 있을 경우
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i < cookies.length; i++) {
//				System.out.println("cookies[i].getName() = " + cookies[i].getName());
				
				//들어온 적이 있는 경우
				if(cookies[i].getName().equals("cookie" + dto.getAmuse_id())) {
					viewCookie = cookies[i];
				}
			}
		}
		
		//viewCookie 값이 없다는 건 들어온 적이 없음을 의미
		if(viewCookie == null) {
			System.out.println("쿠키 없음");
			//쿠키 생성(이름, 값)
			Cookie newCookie = new Cookie("cookie" + dto.getAmuse_id(), "|" + dto.getAmuse_id() + "|");
			//쿠키 추가
			response.addCookie(newCookie);
			
			//조회수 증가
			this.amusementService.updateAmuseView(map);
		}
		//viewCookie 값이 있다는 건 들어온 적이 있음을 의미
		else {
			System.out.println("쿠키 있음");
			String value = viewCookie.getValue();
			
			System.out.println("cookie 값 = " + value);
		}
		//==========================================================
		
		mav.addObject("member_id", session.getAttribute("member_id"));
		mav.addObject("facility", fList);
		mav.addObject("rides", rList);
		mav.addObject("a_images", aImgList);
		mav.addObject("dto", dto);
		mav.addObject("outSpecialList", outSpecialList);
		
		mav.setViewName("amusementDetail");
		return mav;
	}
	
	//paging ajax
	@PostMapping("/reviewPaging")
	public ModelAndView reviewPaging(@RequestParam Map<String, Object> map){
		map.put("countPerPage", countPerPage);
		int pagingNum = (Integer.parseInt(String.valueOf(map.get("currentPage")))-1) * Integer.parseInt(String.valueOf(map.get("countPerPage")));
		map.put("pagingNum", pagingNum);
		
		List<AmuseReviewDTO> reviewList = this.amuseReviewService.reviewSearchTotalList(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewList", reviewList);
		mav.setViewName("test/reviewPaging");
		
		return mav;
	}
	
	//ridesDetail에 ajax
	@GetMapping("/ridesAjax")
	public ModelAndView ridesAjax(@RequestParam Map<String, Object> map){
		ModelAndView mav = new ModelAndView();
		System.out.println("===============" + map + "================");
		
		RidesDTO dto = this.ridesService.getOneRidesDTO(map);
		
		System.out.println("dto = " + dto);
		
		mav.addObject("ride", dto);
		mav.setViewName("test/ridesAjax");
		
		return mav;
	}

	
	//==========rides==========
	@GetMapping("/rides")
	public ModelAndView ridesList(ModelAndView mav) {
		List<RidesDTO> rList = this.ridesService.selectRidesList();
		mav.addObject("rlist", rList);
		mav.setViewName("list");
		return mav;
	}

	@GetMapping("/ridesList")
	@ResponseBody
	public List<RidesDTO> ridesList(@RequestParam Map<String, Object> map){
		System.out.println(map.get("amuse_id"));
		List<RidesDTO> rList = this.ridesService.selectRidesList2(map);
		System.out.println(rList);
		return rList;
	}
	
	//놀이기구 상세보기
	@GetMapping("/ridesDetail")
	public ModelAndView ridesDetail(@RequestParam Map<String, Object> map, 
			ModelAndView mav) {
		System.out.println(map);
		RidesDTO dto = this.ridesService.getOneRidesDTO(map);
		List<RidesDTO> rList = this.ridesService.selectRidesList2(map);
		List<AmusementDTO> inList = this.amusementService.inAmusementList();
		List<AmusementDTO> outList = this.amusementService.outAmusementList();
		mav.addObject("list", rList);
		mav.addObject("inList", inList);
		mav.addObject("outList", outList);
		mav.addObject("dto", dto);
		mav.setViewName("ridesDetail");
		return mav;
	}
	
	@GetMapping("/ridesDetailAjax")
	@ResponseBody
	public RidesDTO ridesDetailAjax(@RequestParam Map<String, Object> map){
		RidesDTO dto = this.ridesService.getOneRidesDTO(map);
		return dto;
	}

	//==========facility==========
	@GetMapping("/facilityDetail")
	@ResponseBody
	public FacilityDTO facilityDatail(@RequestParam Map<String, Object> map){
		FacilityDTO fDetail = this.facilityService.facilityDetail(map);
		return fDetail;
	}
	
	@PostMapping("/insertReview")
	@ResponseBody
	public void amuseReviewList(@RequestParam Map<String, Object> map,
			HttpServletResponse response) throws IOException {
		
		int res = this.amuseReviewService.insertAmuseReview(map);
		
		//1) max(review_id)
		int maxReviewId = this.amuseReviewService.maxReviewId();
		System.out.println("maxReviewId = " + maxReviewId);
		
		map.put("review_id", maxReviewId);
		
		this.amuseReviewService.updateRef(map);
		
		PrintWriter out = response.getWriter();
		
		if(res == 1) {
			out.print("success");
		}
		else {
			out.print("fail");
		}
	}
	
	//review delete
	@GetMapping("/deleteReview")
	public void deleteReview(@RequestParam Map<String, Object> map, HttpServletResponse response) throws ServletException, IOException{
		//review delete
		System.out.println("delete review에서 map = " + map);
		
		this.amuseReviewService.deleteReview(map);
		
		System.out.println("삭제 성공");
		
		PrintWriter out = response.getWriter();
		
		out.print("delete");
	}
	
	@PostMapping("/reviewList")
	public ModelAndView amuseReviewList(@RequestParam Map<String, Object> map, ModelAndView mav){
		map.put("countPerPage", countPerPage);
		//insert 이후에 첫 페이지를 보여줘야 하기 때문
		int pagingNum = 0;
		map.put("pagingNum", pagingNum);
		//System.out.println("insert 이후 review list 불러올 때 map = " + map);
		List<AmuseReviewDTO> arList = this.amuseReviewService.reviewSearchTotalList(map);
		
		mav.addObject("reviewList", arList);
		mav.setViewName("test/reviewPaging");
		
		return mav;
	}
	
	
	//review answer
	@PostMapping("/insertAnswer")
	public ModelAndView insertAnswer(@RequestParam Map<String, Object> map,
			ModelAndView mav){
		System.out.println("===================== insert map = " + map + "=====================");
		
		//review answer insert
		int maxReviewId = this.amuseReviewService.maxReviewId();
		//답글의 review_id
		int ansReviewId = maxReviewId + 1;
		
		//부모글의 amuseReviewDTO 받아오기
		AmuseReviewDTO arDTO = this.amuseReviewService.getOneAmuseReviewDTO2(map);
		
		map.put("seq", arDTO.getSeq());
		map.put("ref", arDTO.getRef());
		
		//부모글과 ref가 같은 row들의 seq가 부모글의 seq보다 크면 모두 +1
		this.amuseReviewService.updateSeq(map);
		
		//답글 insert 하기
		//review_id, amuse_id, member_id, r_content, r_ref, r_seq, r_level
		map.put("review_id", ansReviewId);
		map.put("seq", arDTO.getSeq()+1);
		map.put("level", arDTO.getLevel()+1);
		
		System.out.println("=====================" + map + "=====================");
		
		this.amuseReviewService.insertReviewAnswer(map);
		map.put("countPerPage", countPerPage);
		int pagingNum = (Integer.parseInt(String.valueOf(map.get("currentPage")))-1) * Integer.parseInt(String.valueOf(map.get("countPerPage")));
		map.put("pagingNum", pagingNum);
		
		List<AmuseReviewDTO> reviewList = this.amuseReviewService.reviewSearchTotalList(map);
		mav.addObject("reviewList", reviewList);
		
		mav.setViewName("test/reviewPaging");
		return mav;
	}
	
	//chat
	@GetMapping("/chatRoom")
	public ModelAndView boardSearch(ModelAndView mav, HttpSession session) { 
		List<WebSocketSession> connectSessions = new ArrayList<WebSocketSession>();
		for(WebSocketSession s : connectSessions) {
			System.out.println(s.getId());
		}
		
		String member_id = (String)session.getAttribute("member_id");
		
		mav.addObject("member_id", member_id);
		mav.addObject("sessionList", connectSessions);
		mav.setViewName("test/chatRoom");
		
		return mav;
	}
	
}
