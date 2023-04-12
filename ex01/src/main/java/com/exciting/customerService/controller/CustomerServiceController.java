package com.exciting.customerService.controller;

import java.io.File;
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

@Controller
@Data
@Log4j2
public class CustomerServiceController {

	private static final String BOARD_SAVE_PATH ="D:\\Kdigital\\spring\\springws\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ex01\\resources\\upload\\" ;
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
			cus.totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
			cus.pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
			cus.pageTemp = String.valueOf(map.get("pageNum"));
			if (cus.pageTemp != "null" && !cus.pageTemp.equals(""))
				cus.pageNum = Integer.parseInt(cus.pageTemp);
			cus.start = (cus.pageNum - 1) * cus.pageSize+1;  // 첫 게시물 번호
			cus.end = cus.pageNum * cus.pageSize; // 마지막 게시물 번호
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
	public ModelAndView announcementPost(@RequestParam Map<String,Object> map,@RequestParam(value="file",required = false) List<MultipartFile> mf) {
		ModelAndView mav = new ModelAndView();
		
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
		service.insertAnnouncement(map);
		Map<String,Object> customer = service.selectAnnouncement(map);
		//System.out.println("-----------------------------------------"+customer);
		Map<String,Object> fi = new HashMap<>();
		
		try {
			if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals("")) ) {
		
				for(MultipartFile file:mf) {
					String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+originalFileName);
					String safeFile = BOARD_SAVE_PATH +originalFileName;
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
		System.out.println("나는 고객상세++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+boardImg);
		
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
	public ModelAndView updateBoardpost(@RequestParam Map<String,Object> map,@RequestParam(value="file",required = false) List<MultipartFile> mf){
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
					String safeFile = BOARD_SAVE_PATH +originalFileName;
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
	public int deleteBoardImg(@RequestParam Map<String,Object>map){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++"+map);
		List<Map<String, Object>> img = service.customerImgSelect(map);
		
		
		for(Map<String,Object> rs : img) {
			//System.out.println("11111111111111111111111111111111111111111"+rs.get("boardImg"));
			File file =  new File(BOARD_SAVE_PATH+rs.get("boardImg"));
			if(file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제	
			}
		}
		//db삭제
		int rs = service.customerImgDelete(map);
				
		return rs;
	}
	

}
