package com.exciting.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.dto.Criteria;
import com.exciting.dto.MemberDTO;
import com.exciting.dto.MypageDTO;
import com.exciting.dto.PagingDTO;
import com.exciting.login.service.LoginService;
import com.exciting.mypage.service.MypageService;

import utils.Gmail;
import utils.Sha256;

@RestController
public class MypageAjaxController {

	@Autowired
	MypageService mypageService;
	
	@Autowired
	LoginService loginService;
	
//	writeList.jsp 에서 최신순 목록 클릭시 오는곳
	@RequestMapping(value = "/latestPost")
	@ResponseBody
	public ModelAndView latestPost(Criteria cri,HttpServletRequest request){
		System.out.println("/latestPost2 진입!!");
		
		ModelAndView mav = new ModelAndView();
		
		
		int limitNum;

//		현재페이지(pageNum), 한페이지에 포현하는 개시물수(amount) 설정
		if(cri.getPageNum() == 0 ) {
			System.out.println("pageNum == 0 진입");
			cri.setPageNum(1);
			limitNum = 1;
			cri.setLimitNum(limitNum);
		}else {
			int pageNum = cri.getPageNum();
			limitNum = (pageNum-1)*10+1;
			cri.setLimitNum(limitNum);
		}
		
		cri.setAmount(10);
		
//		member_id가 criteria에 들어가있지 않으면 session에서 가져와서 넣어준다.
		if(cri.getMember_id() == null || cri.getMember_id() == "") {
			System.out.println("cri.member_id is null 진입");
			HttpSession session = request.getSession();
			String member_id = session.getAttribute("member_id")+"";
			cri.setMember_id(member_id);
		}
		
//		member_id에 따른 총 게시물수(total) 계산 (모든 table 합해서 계산)
		int total = mypageService.countUnionAll(cri);
		System.out.println("countUnionAll/total : "+total);
		
//		페이징에 필요한 값들을 받아온다
		PagingDTO pageMaker = new PagingDTO(cri,total);
		System.out.println("pageMaker :"+pageMaker);

		System.out.println("/writeList.get()/limitNum : " + cri.getLimitNum());
//		pageNum, amount 에 따른 한화면에 표시할 게시물들을 가져온다.(모든 table 합해서 가져온다)
		List<MypageDTO> list = mypageService.selectUnionAll(cri);
		
		System.out.println("list :"+list);
		
		 Map<String,Object> map = new HashMap<String,Object>();
		 
		 mav.addObject("list",list);
		 mav.addObject("pageMaker",pageMaker);
		 mav.addObject("total",total);
		 mav.setViewName("/mypage/writeListAjax");	
		 
		return mav;
	}
	
	
//	writeList.jsp 에서 과거순 목록 클릭시 오는곳	
	@RequestMapping(value = "/pastPost")
	@ResponseBody
	public ModelAndView pastPost(Criteria cri,HttpServletRequest request,HttpServletResponse response){
		System.out.println("/pastPost 진입!!");
		
		ModelAndView mav = new ModelAndView();
		
		int limitNum;

//		현재페이지(pageNum), 한페이지에 포현하는 개시물수(amount) 설정
		if(cri.getPageNum() == 0 ) {
			System.out.println("pageNum == 0 진입");
			cri.setPageNum(1);
			limitNum = 1;
			cri.setLimitNum(limitNum);
		}else {
			int pageNum = cri.getPageNum();
			limitNum = (pageNum-1)*10+1;
			cri.setLimitNum(limitNum);
		}
		
		cri.setAmount(10);
		
//		member_id가 criteria에 들어가있지 않으면 session에서 가져와서 넣어준다.
		if(cri.getMember_id() == null || cri.getMember_id() == "") {
			System.out.println("cri.member_id is null 진입");
			HttpSession session = request.getSession();
			String member_id = session.getAttribute("member_id")+"";
			cri.setMember_id(member_id);
		}
		
//		member_id에 따른 총 게시물수(total) 계산 (모든 table 합해서 계산)
		int total = mypageService.countUnionAll(cri);
		System.out.println("countUnionAll/total : "+total);
		
//		페이징에 필요한 값들을 받아온다
		PagingDTO pageMaker = new PagingDTO(cri,total);
		System.out.println("pageMaker :"+pageMaker);

		System.out.println("/writeList.get()/limitNum : " + cri.getLimitNum());
//		pageNum, amount 에 따른 한화면에 표시할 게시물들을 가져온다.(모든 table 합해서 가져온다)
		List<MypageDTO> list = mypageService.selectUnionAllAsc(cri);
		
		System.out.println("list :"+list);
		
		 Map<String,Object> map = new HashMap<String,Object>();
		 
		 mav.addObject("list",list);
		 mav.addObject("pageMaker",pageMaker);
		 mav.addObject("total",total);
		 mav.setViewName("/mypage/writeListAjaxAsc");	
		 
		return mav;
	}
	
	
}
