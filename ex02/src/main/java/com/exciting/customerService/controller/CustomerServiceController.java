package com.exciting.customerService.controller;

import java.io.File;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.board.service.BoardService;
import com.exciting.customerService.service.CustomerService;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import utils.ChangeJava;
import utils.ChangeJavanontextarea;
import utils.BoardPage;
import utils.ChangeHtml;

@RestController
@Data
@Log4j2
public class CustomerServiceController {

	private static final String BOARD_SAVE_PATH ="/resources/upload/" ;
	private static final String HOME_BOARD_SAVE_PATH ="C:\\Users\\MOON\\git\\repository2\\ex01\\src\\main\\webapp\\resources\\upload\\" ;
	private static final String BOARD_LOAD_PATH ="/resources/upload/" ;
	
	int totalCount;
//	System.out.println("*******************************"+totalCount);
//	System.out.println("*******************************"+res.get("cnt"));
	int pageSize;
	int blockPage;
	int totalPage;
	int pageNum;
	String pageTemp;
	int start;  // 첫 게시물 번호
	int end; // 마지막 게시물 번호
	String paging;
	int start2;
	
	ChangeJava changeJava = new ChangeJava();
	ChangeHtml changeHtml = new ChangeHtml();
	ChangeJavanontextarea changeJavanontextarea = new ChangeJavanontextarea();
	@Autowired
	CustomerService service;
	@Autowired
	private ServletContext servletContext;

	
	@RequestMapping(value = "/customer/announcement", method = RequestMethod.GET)
	public ModelAndView Board(@RequestParam Map<String,Object> map,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//System.out.println(request.getRequestURI());
		
		
		Map<String,Object> res = service.selectAnnouncementCnt(map);
		CustomerServiceController cus = new CustomerServiceController();
		//System.out.println(res);
		if(res.size() !=0) {
			cus.totalCount = Integer.parseInt(res.get("cnt").toString());
//			System.out.println("*******************************"+totalCount);
//			System.out.println("*******************************"+res.get("cnt"));
			cus.pageSize =10;
			cus.blockPage = 10;
			cus.totalPage = (int)Math.ceil((double)cus.totalCount / cus.pageSize); // 전체 페이지 수
			cus.pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
			cus.pageTemp = String.valueOf(map.get("pageNum"));
			if (cus.pageTemp != "null" && !cus.pageTemp.equals(""))
				cus.pageNum = Integer.parseInt(cus.pageTemp);
			cus.start = (cus.pageNum - 1) * cus.pageSize+1;  // 첫 게시물 번호
			cus.end = 10; // 마지막 게시물 번호
			String paging = BoardPage.customerstr(cus.totalCount, cus.pageSize, cus.blockPage, cus.pageNum, request.getRequestURI());
			int start2 = cus.start-1;
			
			map.put("start", start2);
			map.put("end", cus.end);
			
			List<Map<String,Object>>list = service.selectAnnouncementList(map);
			//System.out.println(list);
			
			//사진업로드
			for(Map<String,Object> map2:list) {
				String date = map2.get("postdate").toString();
				String ymd=date.substring(0,10);
				String ymd2=ymd.replaceAll("-",".");
				String hms=date.substring(11);
				String postdate=ymd2+" "+hms;
				//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
				String c_content = ChangeJavanontextarea.change(String.valueOf(map2.get("c_content")));
				map2.put("c_content", c_content);
				String c_title = ChangeJavanontextarea.change(String.valueOf(map2.get("c_title")));
				map2.put("c_title", c_title);
				
				//리스트가 존재하지않을시
				if(list.size()!=0) {
					map2.put("postdate", postdate);
					map2.put("cnt", list.size());
				}
			}
			
			
			mav.addObject("announcementList",list);
			mav.addObject("paging",paging);
		}else {
			mav.addObject("paging","<span class=page-item>1</span>");;
		}
		
		//텍스트 처리
		
		mav.setViewName("/customerService/announcement");
		
		return mav;
	}
	
	@RequestMapping(value = "/customer/insertAnnouncement", method = RequestMethod.GET)
	public ModelAndView announcementGet(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/customerService/insertannouncement");
		return mav;
	}
	
	@RequestMapping(value = "/customer/insertAnnouncement", method = RequestMethod.POST)
	public ModelAndView announcementPost(@RequestParam Map<String,Object> map,@RequestParam(value="file",required = false) List<MultipartFile> mf,HttpSession session,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
		service.insertAnnouncement(map);
		
		String c_title = ChangeHtml.change(String.valueOf(map.get("c_title")));
		map.put("c_title", c_title);
		String c_content = ChangeHtml.change(String.valueOf(map.get("c_content")));
		map.put("c_content", c_content);
		
		Map<String,Object> customer = service.selectAnnouncement(map);
		System.out.println("-----------------------------------------"+customer);
		Map<String,Object> fi = new HashMap<>();
		
		try {
			if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals("")) ) {
		
				for(MultipartFile file:mf) {
					String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+originalFileName);
					String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
					String safeFile = uploadDir+"/"+originalFileName;
					fi.put("announcement_num", customer.get("announcement_num"));
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+safeFile);
			
					fi.put("boardImg", originalFileName);		
					service.customerImg(fi);
					fi.remove("boardImg");
					file.transferTo(new File(safeFile));
					
				}	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("redirect:/customer/announcement");
		return mav;
	}
	
	@RequestMapping(value = "/customer/view", method = RequestMethod.GET)
	public ModelAndView announcementView(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		
		//view
		Map<String,Object> viewOne = service.selectAnnouncement(map);
		List<Map<String,Object>> boardImg = service.customerImgSelect(map);
		//System.out.println("나는 고객상세++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+boardImg);
		
		for(Map<String, Object> img : boardImg) {
			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
		}
		
		// 특수문자처리
		String c_content = ChangeJavanontextarea.change(String.valueOf(viewOne.get("c_content")));
		viewOne.put("c_content", c_content);
		String c_title = ChangeJavanontextarea.change(String.valueOf(viewOne.get("c_title")));
		viewOne.put("c_title", c_title);
		
		//날짜처리
		String date = viewOne.get("postdate").toString();
		String ymd=date.substring(0,10);
		String ymd2=ymd.replaceAll("-",".");
		String hms=date.substring(11);
		String postdate=ymd2+" "+hms;
		viewOne.put("postdate", postdate);
		
		
		mav.addObject("boardImg", boardImg);
		mav.addObject("view",viewOne);
		mav.setViewName("/customerService/announcementView");
		return mav;
	}
	
	
	@RequestMapping(value = "/customer/updateAnnouncement", method = RequestMethod.GET)
	public ModelAndView updateBoard(@RequestParam Map<String,Object>map){
		ModelAndView mav = new ModelAndView();
		Map<String, Object> customer = service.selectAnnouncement(map); 
		
		
		
		String c_title = ChangeJava.change(String.valueOf(customer.get("c_title")));
		customer.put("c_title", c_title);
		String c_content = ChangeJava.change(String.valueOf(customer.get("c_content")));
		customer.put("c_content", c_content);
		
		
		mav.addObject("boardView",customer);
		mav.setViewName("/customerService/updateAnnouncement");
		return mav;
	}
	
	@RequestMapping(value = "/customer/updateBoard", method = RequestMethod.POST)
	public ModelAndView updateBoardpost(@RequestParam Map<String,Object> map,@RequestParam(value="file",required = false) List<MultipartFile> mf,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
	
		Map<String,Object> fi = new HashMap<>();

		Map<String,Object> board_id = service.selectAnnouncement(map);
		
		//System.out.println("mf+++++++++++++++++++++++++++++++++++++++"+mf.get(0).getOriginalFilename());

		System.out.println("map++++++++++++++++++++++++++++"+map);
		
		//이미지 넣기
		try {
			if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals("")) ) {
		
				for(MultipartFile file:mf) {
					String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+originalFileName);
					String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
					String safeFile = uploadDir+"/"+originalFileName;
					fi.put("announcement_num", board_id.get("announcement_num"));
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+safeFile);
			
					fi.put("boardImg", originalFileName);		
					service.customerImg(fi);
					fi.remove("boardImg");
					file.transferTo(new File(safeFile));
					
				}	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		String c_title = ChangeHtml.change(String.valueOf(map.get("c_title")));
		map.put("c_title", c_title);
		String c_content = ChangeHtml.change(String.valueOf(map.get("c_content")));
		map.put("c_content", c_content);
		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+map);
		service.updateannouncement(map); 
		mav.setViewName("redirect:/customer/view?announcement_num="+map.get("announcement_num"));
		return mav;
	}
	
	@RequestMapping(value = "/customer/deleteAnnouncement", method = RequestMethod.GET)
	public ModelAndView  deleteAnnouncement(@RequestParam Map<String,Object> map,HttpServletRequest request) {
		System.out.println(map);
		ModelAndView mav = new ModelAndView();
		List<Map<String, Object>> img = service.customerImgSelect(map);
		service.deleteAnnouncement(map);
		service.customerImgDelete(map);
		for(Map<String,Object> rs : img) {
			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
			if(file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제	
			}
		}
		mav.setViewName("redirect:/customer/announcement");
		//mav.addObject("member_id",map.get("inquiry_num"));
		return mav;
	}
	
	
	@RequestMapping(value = "/customer/boardImgShow", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> boardImgShow(@RequestParam Map<String,Object>map){
		//System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		//System.out.println("ajax 이미지1+++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
		List<Map<String,Object>> list = service.customerImgSelect(map);
		
		//System.out.println("ajax 이미지2+++++++++++++++++++++++++++++++++++++++++++++++++++"+list);

		if(list.size()!=0) {			
			for(Map<String,Object> img : list){
				img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
			}
		}
		
		
		return list;
	}
	
	
	@RequestMapping(value = "/customer/deleteBoardImg", method = RequestMethod.POST)
	@ResponseBody
	public int deleteBoardImg(@RequestParam Map<String,Object>map,HttpServletRequest request){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++"+map);
		List<Map<String, Object>> img = service.customerImgSelect(map);
		
		
		for(Map<String,Object> rs : img) {
			//System.out.println("11111111111111111111111111111111111111111"+rs.get("boardImg"));
			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
			if(file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제	
			}
		}
		//db삭제
		int rs = service.customerImgDelete(map);
				
		return rs;
	}
	
	
	@RequestMapping(value = "/customer/faq", method = RequestMethod.GET)
	public ModelAndView faqget(@RequestParam Map<String,Object> map,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//페이징
		//검색은 필요없을거같음
		String f_type= null;
		CustomerServiceController cus = new CustomerServiceController();
		if(String.valueOf(map.get("f_type")).equals("null")) {
			//System.out.println("여기 통과는 했니?");
			map.put("f_type", "0");
			f_type=String.valueOf(map.get("f_type"));
		}else {
			//System.out.println("여기로 왔니?");
			f_type=String.valueOf(map.get("f_type"));
		}
		List<Map<String,Object>> faqCnt = service.getFaqList(map);

		cus.totalCount = Integer.parseInt(String.valueOf(faqCnt.get(0).get("cnt")));
		cus.pageSize =5;
		cus.blockPage = 10;
		cus.totalPage = (int)Math.ceil((double)cus.totalCount / cus.pageSize); // 전체 페이지 수
		cus.pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
		cus.pageTemp = String.valueOf(map.get("pageNum"));
		if (cus.pageTemp != "null" && !cus.pageTemp.equals(""))
			cus.pageNum = Integer.parseInt(cus.pageTemp);
		cus.start = (cus.pageNum - 1) * cus.pageSize+1;  // 첫 게시물 번호
		cus.end = 5; // 마지막 게시물 번호
		String paging = BoardPage.customerfaq(cus.totalCount, cus.pageSize, cus.blockPage, cus.pageNum, request.getRequestURI(),f_type);
		int start2 = cus.start-1;
		
		

		//mav.addObject("list",faqList);
		mav.setViewName("/customerService/faq");
		mav.addObject("paging",paging);
		mav.addObject("start",start2);
		return mav;
	}
	
	
	//이거 나중에 업데이트로 이용하셈 
	@RequestMapping(value = "/customer/getfaqList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> getmap2(@RequestParam Map<String,Object> map) {
		//System.out.println("+++++++++++++너왔어?"+map);
		
		CustomerServiceController cus = new CustomerServiceController();
		System.out.println(map.get("start"));
		
		map.put("start", map.get("start"));
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++"+map);
		List<Map<String,Object>> faqList = service.getFaqList(map);
		
		for(Map<String,Object> map2 : faqList) {
			
			//고객read
			String content = ChangeJavanontextarea.change(String.valueOf(map2.get("content")));
			map2.put("content", content);
			String title = ChangeJavanontextarea.change(String.valueOf(map2.get("title")));
			map2.put("title", title);
			
			
		
		}
		//자주묻는 질문 내용 불러오기
		//페이징
		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
		//검색은 필요없을거같음
		
		return faqList;
	}
	
	@RequestMapping(value = "/customer/faqWrite", method = RequestMethod.POST)
	@ResponseBody
	public int faqWrite(@RequestParam Map<String,Object> map) {
		
		String title = ChangeHtml.change(String.valueOf(map.get("title")));
		map.put("title", title);
		String content = ChangeHtml.change(String.valueOf(map.get("content")));
		map.put("content", content);
		
		int rs=service.faqWrite(map);
		//자주묻는 질문 내용 불러오기
		//페이징
		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
		//검색은 필요없을거같음
		
		return rs;
	}
	
	@RequestMapping(value = "/customer/faqUpdate", method = RequestMethod.POST)
	@ResponseBody
	public int faqUpdate(@RequestParam Map<String,Object> map) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
		String title = ChangeHtml.change(String.valueOf(map.get("title")));
		map.put("title", title);
		String content = ChangeHtml.change(String.valueOf(map.get("content")));
		map.put("content", content);
		
		int rs= service.faqupdate(map);
		System.out.println("-----------------------------------------------"+rs);
		//자주묻는 질문 내용 불러오기
		//페이징
		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
		//검색은 필요없을거같음
		return rs;
	}
	
	@RequestMapping(value = "/customer/faqDelete", method = RequestMethod.POST)
	@ResponseBody
	public int faqDelete(@RequestParam Map<String,Object> map) {
		
		int rs=service.faqDelete(map);
		//자주묻는 질문 내용 불러오기
		//페이징
		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
		//검색은 필요없을거같음
		return rs;
	}
	
	
	@RequestMapping(value = "/customer/announcementInquiry", method = RequestMethod.GET)
	public ModelAndView announcementInquiryGET(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/customerService/announcementInquiry");
		return mav;
	}
	
	@RequestMapping(value = "/customer/announcementInquiry", method = RequestMethod.POST)
	public ModelAndView announcementInquiryPOST(@RequestParam Map<String,Object> map,@RequestParam(value="file",required = false) List<MultipartFile> mf,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String,Object> fi = new HashMap<>();
		map.put("b_title", "(답변 대기중)"+map.get("b_title"));
		service.insertAnnouncementInquiry(map);
		//Map<String,Object> customer = service.consultationView(map);
		//fi.put("ref", map.get("inquiry_num"));
		//ref 업데이트
		service.updateRefInquiry(map);
		fi.remove("ref");
		try {
			if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals("")) ) {
		
				for(MultipartFile file:mf) {
					String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+originalFileName);
					String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
					String safeFile = uploadDir+"/"+originalFileName;
					fi.put("inquiry_num", map.get("inquiry_num"));
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+safeFile);
			
					fi.put("boardImg", originalFileName);		
					service.customerImg(fi);
					fi.remove("boardImg");
					file.transferTo(new File(safeFile));
					
				}	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("redirect:/customer/consultationDetails");
		return mav;
	}
	
	@RequestMapping(value = "/customer/consultationDetails", method = RequestMethod.GET)
	public ModelAndView consultationDetailsGET(@RequestParam Map<String,Object> map,HttpSession session,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("member_id")!=null) {
			map.put("member_id", session.getAttribute("member_id"));
			map.put("m_admin", session.getAttribute("m_admin"));
			CustomerServiceController cus = new CustomerServiceController();
			
			List<Map<String,Object>> consultationDetailsList =new ArrayList<>();
			Map<String,Object> res = service.selectconsultationDetailsCnt(map);
			String paging=null;
			
			
			if(res.size()!=0) {
				
					cus.totalCount = Integer.parseInt(res.get("cnt").toString());
	//				System.out.println("*******************************"+totalCount);
	//				System.out.println("*******************************"+res.get("cnt"));
					cus.pageSize =10;
					cus.blockPage = 10;
					cus.totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
					cus.pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
					cus.pageTemp = String.valueOf(map.get("pageNum"));
					if (cus.pageTemp != "null" && !cus.pageTemp.equals(""))
						cus.pageNum = Integer.parseInt(cus.pageTemp);
					cus.start = (cus.pageNum - 1) * cus.pageSize+1;  // 첫 게시물 번호
					cus.end = 10; // 마지막 게시물 번호
					paging = BoardPage.customerstr(cus.totalCount, cus.pageSize, cus.blockPage, cus.pageNum, request.getRequestURI());
					int start2 = cus.start-1;
					
					map.put("start", start2);
					map.put("end", cus.end);
					
					consultationDetailsList =service.consultationDetails(map);
	
					for(Map<String,Object> map2:consultationDetailsList) {
					
					String date = map2.get("postdate").toString();
					String ymd=date.substring(0,10);
					String ymd2=ymd.replaceAll("-",".");
					String hms=date.substring(11);
					String postdate=ymd2+" "+hms;
					//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
					String b_content = ChangeJavanontextarea.change(String.valueOf(map2.get("b_content")));
					map2.put("b_content", b_content);
					String b_title = ChangeJavanontextarea.change(String.valueOf(map2.get("b_title")));
					map2.put("b_title", b_title);
					
					//리스트가 존재하지않을시
					if(consultationDetailsList.size()!=0) {
						map2.put("postdate", postdate);
						map2.put("cnt", consultationDetailsList.size());
					}
					mav.addObject("list",consultationDetailsList);
					mav.addObject("paging",paging);
				}
			}
		}
		
		
		
		mav.setViewName("/customerService/consultationDetails");
		
		
		
		return mav;
	}
	
	@RequestMapping(value = "/customer/consultationView", method = RequestMethod.GET)
	public ModelAndView consultationViewGET(@RequestParam Map<String,Object> map,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		map.put("m_admin",session.getAttribute("m_admin") );
		List<Map<String, Object>> consultationView = service.consultationDetails(map);
		List<Map<String,Object>> boardImg = service.selectCustomImg(map);
		
		
		for(Map<String, Object> img : boardImg) {
			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
		}
				
		for(Map<String, Object> map2 : consultationView) {
			String date = map2.get("postdate").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replaceAll("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
			String b_content = ChangeJavanontextarea.change(String.valueOf(map2.get("b_content")));
			map2.put("b_content", b_content);
			String b_title = ChangeJavanontextarea.change(String.valueOf(map2.get("b_title")));
			map2.put("b_title", b_title);

			map2.put("postdate", postdate);
			map2.put("cnt", consultationView.size());
		}
				
				
			
		mav.addObject("boardImg", boardImg);
		mav.addObject("view",consultationView);
		mav.setViewName("/customerService/consultationView");
		return mav;
	}
	
	@RequestMapping(value = "/customer/consultationView", method = RequestMethod.POST)
	public ModelAndView  consultationViewPOST(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		String b_title = map.get("b_title").toString().replace("(답변 대기중)", "(답변 완료)");
		map.put("b_title",b_title );
		service.insertConsultation(map);
		service.updateTitleInquiry(map);
		mav.setViewName("redirect:/customer/consultationView");
		mav.addObject("inquiry_num",map.get("inquiry_num"));
		return mav;
	}
	
	@RequestMapping(value = "/customer/deleteconsultationView", method = RequestMethod.GET)
	public ModelAndView  deleteconsultationView(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		service.deleteconsultationView(map);
		mav.setViewName("redirect:/customer/consultationDetails");
		mav.addObject("member_id",map.get("member_id"));
		return mav;
	}
	
	

}
