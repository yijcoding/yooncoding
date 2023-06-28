package com.exciting.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.dao.LoginDAO;
import com.exciting.dto.Criteria;
import com.exciting.dto.MemberDTO;
import com.exciting.dto.MypageDTO;
import com.exciting.dto.PagingDTO;
import com.exciting.login.service.LoginService;
import com.exciting.mypage.service.MypageService;


@Controller
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	@Autowired
	MypageService mypageService;
	
	@Autowired
	LoginService loginService;

	public MypageController() {
		System.out.println("@Controller 스프링 자동생성");
	}

//	내 게시물 보기 링크 누르면 오는곳
	@GetMapping(value = "/writeList")
	public ModelAndView writeList(Criteria cri,HttpServletRequest request) {
		System.out.println("/writeList.get() 접근");
				
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
		PagingDTO d = new PagingDTO(cri,total);


		System.out.println("/writeList.get()/limitNum : " + cri.getLimitNum());
//		pageNum, amount 에 따른 한화면에 표시할 게시물들을 가져온다.(모든 table 합해서 가져온다)
		List<MypageDTO> list = mypageService.selectUnionAll(cri);
		
		System.out.println("list :"+list);
		
		mav.addObject("list",list);
		mav.addObject("total",total);
		mav.addObject("pageMaker",d);
		
		/* mav.setViewName("mypage/writeList"); */
		mav.setViewName("mypage/writeList");

		return mav;
	}
	
//  마이페이지의 회원수정 누르면 오는곳
	@GetMapping(value = "/editMember")
	public ModelAndView editMemberGet(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/editMember.get() 접근");
				
		ModelAndView mav = new ModelAndView();
		
//		session에서 member_id값 받아온다음 member_id에 해당하는 정보를 가져와서 회원정보수정에 표출한다
		HttpSession session = request.getSession();
		String member_id = session.getAttribute("member_id")+"";
		System.out.println("member_id : "+member_id);
		dto.setMember_id(member_id);
		
		MemberDTO d = loginService.selectMember(dto);
		
		mav.addObject("dto",d);
		mav.setViewName("mypage/editMember");

		return mav;
	}
	
//  회원수정페이지에서 수정하기 버튼 누르면 오는곳
	@PostMapping(value = "/editMember")
	public ModelAndView editMemberPost(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/editMember.post() 접근");
				
		ModelAndView mav = new ModelAndView();
		
//		session에서 member_id값 받아온다음 member_id에 해당하는 정보를 가져와서 회원정보수정에 표출한다
		HttpSession session = request.getSession();
		String member_id = session.getAttribute("member_id")+"";
		dto.setMember_id(member_id);
		
//		회원이 수정란에 입력한값
		String day = dto.getM_day();
		String month = dto.getM_month();
		String year = dto.getM_year();
		System.out.println("day : " + day);
		
		String m_birth = year+month+day;
		
		String email1 = dto.getM_email1();
		String email2 = dto.getM_email2();
		
		String m_email = email1+"@"+email2;
		
		String phone1 = dto.getM_phone1();
		String phone2 = dto.getM_phone2();
		String phone3 = dto.getM_phone3();
		
		String m_phone = phone1+phone2+phone3;
		
		dto.setM_birth(m_birth);
		dto.setM_email(m_email);
		dto.setM_phone(m_phone);
		
		loginService.updateMember(dto);
		
		mav.setViewName("mypage/editMemberComplete");

		return mav;
	}
	
//	주소찾기 누르면 오는곳(새창에서 jusoPopup.jsp가 실행된다.)
	@GetMapping(value = "/juso")
	public ModelAndView jusoGet(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/jusoGet 접근");
				
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("login/jusoPopup");

		return mav;
	}
	
//	주소찾기(popup창)에서 주소입력하고 확인 누르면 오는곳
	@PostMapping(value = "/juso")
	public ModelAndView jusoPost(@RequestParam("roadFullAddr") String roadFullAddr) {
		System.out.println("roadFullAddr : "+ roadFullAddr);
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("roadFullAddr",roadFullAddr);
		mav.setViewName("login/jusoPopup");

		return mav;
	}
	
//	/juso.post 거친다음 오는곳
	@PostMapping(value = "/jusoEnd")
	public ModelAndView jusoEndPost(@RequestParam("roadFullAddr") String roadFullAddr) {
		System.out.println("roadFullAddr : "+ roadFullAddr);
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("roadFullAddr",roadFullAddr);
		mav.setViewName("login/jusoPopup");

		return mav;
	}
	
//	menu에서 mypage버튼 누르면 오는곳
	@GetMapping(value = "/mypage")
	public ModelAndView mypage(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/mypage.get() 접근");
				
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		String member_id = session.getAttribute("member_id")+"";
		
		dto.setMember_id(member_id);
		
		dto = loginService.selectMember(dto);
		
		System.out.println("/mypage.get() 종료");
		mav.addObject("dto",dto);
		mav.setViewName("mypage/mypage");

		return mav;
	}
	
//	마이페이지 -> 회원정보수정page에서 회원이미지 수정시 오는 곳 
	@PostMapping("/imageUpload")
	public ModelAndView upload(@RequestParam("file") MultipartFile file,MemberDTO dto,HttpServletRequest request) {
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일 사이즈
		
		System.out.println("파일명 : "  + fileRealName);
		System.out.println("용량크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		
		String uploadFolder = "D:\\kdigital\\spring\\springws\\ex02\\src\\main\\webapp\\resources\\upload\\mypage";
		
		
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜덤 문자를 통해 db와 서버에  저장할 파일명을 새롭게 만들어 준다.
		 */
		
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유문자열" + uniqueName);
		System.out.println("확장자명" + fileExtension);
		
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		
		File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
		try {
			file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String fileName = uniqueName+fileExtension;
		System.out.println("fileName : " + fileName);
		
//		사용자가 등록한 이미지 db에 저장
		dto.setM_image(fileName);
		
		HttpSession session = request.getSession();
		String member_id = session.getAttribute("member_id")+"";
		dto.setMember_id(member_id);
		
		System.out.println("member_id : " + member_id);
		System.out.println("dto : " + dto);
		
		loginService.updateMemberImage(dto);
		loginService.selectMember(dto);
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto",dto);
		mav.addObject("fileName",fileName);
		mav.setViewName("/mypage/editMember");
		
		return mav;
	}
	
}
