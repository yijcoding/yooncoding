package com.exciting.customerService.controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.board.service.BoardService;
import com.exciting.customerService.service.CustomerService;
import com.exciting.dto.AnnouncementDTO;
import com.exciting.dto.BoardImgDTO;
import com.exciting.dto.ResponseDTO;
import com.exciting.entity.AnnouncementEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.utils.ChangeJson;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/customer")
public class CustomerServiceController {

	private static final String BOARD_UPLOAD_PATH;

	static {
		try {
			BOARD_UPLOAD_PATH = new ClassPathResource("static/uploads/").getFile().getAbsolutePath();
		} catch (IOException e) {
			throw new RuntimeException("Failed to get the upload path", e);
		}
	}

	private CustomerService service;

	@Autowired
	public CustomerServiceController(CustomerService service) {
		this.service = service;
	}

	@GetMapping("/announcement")
	@ResponseBody
	public Page<AnnouncementDTO> Announcement(AnnouncementDTO announcementDTO, @RequestParam Map<String, Object> map) {

		AnnouncementEntity entity = AnnouncementDTO.toEntity(announcementDTO);
		int pageNum = Integer.parseInt(String.valueOf(map.get("pageNum")));
		String Search = String.valueOf(map.get("search"));

		Page<AnnouncementDTO> announcementList = service.getAnnouncementList(entity, pageNum,Search);

		

		
		System.out.println(announcementList);

		return announcementList;

		// 텍스트 처리

	}

	/*
	 * 
	 * 이미지 Image 업로드 Upload Start
	 * 
	 */

	@PostMapping("/imageUpload")
	@ResponseBody
	public void imageUpload(@RequestParam(value = "file", required = false) List<MultipartFile> mf,
			@RequestParam(value="announcement_num", required=false) Integer announcement_num,
		    @RequestParam(value="inquiry_num", required=false) Integer inquiry_num) {

		try {
			if (mf != null) {
				String firstFileName = mf.get(0).getOriginalFilename();
				boolean isFileNotEmpty = firstFileName != null && !firstFileName.equals("");
				if (isFileNotEmpty) {

					for (MultipartFile file : mf) {
						BoardImgEntity boardImgEntity = new BoardImgEntity();

						String originalFileName = System.currentTimeMillis() + file.getOriginalFilename();

						String uploadDir = BOARD_UPLOAD_PATH;
						String safeFile = uploadDir + "/" + originalFileName;

						//받은값이 있을시 넣음
						if(announcement_num != null || announcement_num != 0)
							boardImgEntity.setAnnouncement_num(announcement_num);
						else if(inquiry_num != null || inquiry_num != 0)
							boardImgEntity.setInquiry_num(inquiry_num);

						//파일 이름저장
						boardImgEntity.setBoardimg(originalFileName);

						//DB에 저장
						service.customerImgInsert(boardImgEntity);

						//폴더에 저장
						file.transferTo(new File(safeFile));
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardImgInsert Error");
		}

	}

	/*
	 * 
	 * 이미지 Image 업로드 Upload End
	 * 
	 */
	
	
	/*
	 * 
	 * 공지사항 글쓰기 announcement Write Start
	 * 
	 */
	
	//공지사항 글쓰기
	@PostMapping("/insertAnnouncement")
	@ResponseBody
	public int announcementPost(@RequestBody AnnouncementDTO announcementDTO) {
		
		AnnouncementEntity entity = AnnouncementDTO.toEntity(announcementDTO);
		
		
		
		entity.setC_type("공지");
		entity.setPostdate(LocalDateTime.now());
		
		int announcement_num =service.insertAnnouncement(entity);
		
		
		return announcement_num;
	}
	
	/*
	 * 
	 * 공지사항 글쓰기 announcement Write END
	 * 
	 */
	
	
	
	/*
	 * 
	 * 공지사항 세부 announcement Detail Start
	 * 
	 */
	@GetMapping(value ={"/view","/updateAnnouncement"})
	@ResponseBody
	public ResponseEntity<?> announcementView(AnnouncementDTO announcementDTO) {
		
		
		try {
			
			AnnouncementEntity entity = AnnouncementDTO.toEntity(announcementDTO);
			
			AnnouncementEntity announcementData = service.getAnnouncementOne(entity);
			
			
			List<BoardImgEntity> boardImgList = service.getAnnouncementImg(entity);
			
			//DTO변환
			List<BoardImgDTO> boardImgDTO = boardImgList.stream()
				    .filter(Objects::nonNull) // "null" 값인 요소들을 제외하고 필터링
				    .map(BoardImgDTO::new)
				    .collect(Collectors.toList());
			
			//이미지 경로 주입
			int num =0;
			String boardImg =null;
			for(BoardImgDTO i : boardImgDTO) {
				boardImg = i.getBoardimg();
				boardImg = "/uploads/"+ boardImg;
				i.setBoardimg(boardImg);
				num++;
			}
			
			JSONObject jsonData = ChangeJson.ToChangeJson(announcementData);
			
			jsonData.put("boardImg", boardImgDTO);
			System.out.println("/////////////////////////////////////////////////");
			System.out.println(jsonData);
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().json(jsonData).build();
			
			return ResponseEntity.ok().body(response.getJson());
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().error(error).build();
			return ResponseEntity.badRequest().body(response.getError());
		}
		
	}
	/*
	 * 
	 * 공지사항 세부 announcement Detail END
	 * 
	 */

	
	

//	@RequestMapping(value = "/customer/updateAnnouncement", method = RequestMethod.POST)
//	public void updateBoardpost(@RequestBody Map<String,Object> map,@RequestParam(value="file",required = false) List<MultipartFile> mf,HttpServletRequest request){
//	
//		
//
//		Map<String,Object> board_id = service.selectAnnouncement(map);
//		
//		//System.out.println("mf+++++++++++++++++++++++++++++++++++++++"+mf.get(0).getOriginalFilename());
//
//		System.out.println("map++++++++++++++++++++++++++++"+map);
//		
//		//이미지 넣기
//		
//		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+map);
//		service.updateannouncement(map); 
//		
//	}
//	
//	@RequestMapping(value = "/customer/deleteAnnouncement", method = RequestMethod.GET)
//	public ModelAndView  deleteAnnouncement(@RequestParam Map<String,Object> map,HttpServletRequest request) {
//		System.out.println(map);
//		ModelAndView mav = new ModelAndView();
//		List<Map<String, Object>> img = service.customerImgSelect(map);
//		service.deleteAnnouncement(map);
//		service.customerImgDelete(map);
//		for(Map<String,Object> rs : img) {
//			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
//			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
//			if(file.exists()) { // 파일이 존재하면
//				file.delete(); // 파일 삭제	
//			}
//		}
//		mav.setViewName("redirect:/customer/announcement");
//		//mav.addObject("member_id",map.get("inquiry_num"));
//		return mav;
//	}
//	
//	
//	@RequestMapping(value = "/customer/boardImgShow", method = RequestMethod.POST)
//	@ResponseBody
//	public List<Map<String,Object>> boardImgShow(@RequestParam Map<String,Object>map){
//		//System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
//		//System.out.println("ajax 이미지1+++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
//		List<Map<String,Object>> list = service.customerImgSelect(map);
//		
//		//System.out.println("ajax 이미지2+++++++++++++++++++++++++++++++++++++++++++++++++++"+list);
//
//		if(list.size()!=0) {			
//			for(Map<String,Object> img : list){
//				img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
//			}
//		}
//		
//		
//		return list;
//	}
//	
//	
//	@RequestMapping(value = "/customer/deleteBoardImg", method = RequestMethod.POST)
//	@ResponseBody
//	public int deleteBoardImg(@RequestBody Map<String,Object>map,@RequestParam(value="file",required = false) List<MultipartFile> mf,HttpServletRequest request){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++"+map);
//		
//		List<Map<String, Object>> img = service.customerImgSelect(map);
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++"+img);
//		
//		for(Map<String,Object> rs : img) {
//			//System.out.println("11111111111111111111111111111111111111111"+rs.get("boardImg"));
//			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
//			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
//			if(file.exists()) { // 파일이 존재하면
//				file.delete(); // 파일 삭제	
//			}
//		}
//		//db삭제
//		int rs = service.customerImgDelete(map);
//				
//		return rs;
//	}
//	
//	
//	@RequestMapping(value = "/customer/faq", method = RequestMethod.GET)
//	public ModelAndView faqget(@RequestParam Map<String,Object> map,HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		//페이징
//		//검색은 필요없을거같음
//		String f_type= null;
//		CustomerServiceController cus = new CustomerServiceController();
//		if(String.valueOf(map.get("f_type")).equals("null")) {
//			//System.out.println("여기 통과는 했니?");
//			map.put("f_type", "0");
//			f_type=String.valueOf(map.get("f_type"));
//		}else {
//			//System.out.println("여기로 왔니?");
//			f_type=String.valueOf(map.get("f_type"));
//		}
//		List<Map<String,Object>> faqCnt = service.getFaqList(map);
//
//		cus.totalCount = Integer.parseInt(String.valueOf(faqCnt.get(0).get("cnt")));
//		cus.pageSize =5;
//		cus.blockPage = 10;
//		cus.totalPage = (int)Math.ceil((double)cus.totalCount / cus.pageSize); // 전체 페이지 수
//		cus.pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
//		cus.pageTemp = String.valueOf(map.get("pageNum"));
//		if (cus.pageTemp != "null" && !cus.pageTemp.equals(""))
//			cus.pageNum = Integer.parseInt(cus.pageTemp);
//		cus.start = (cus.pageNum - 1) * cus.pageSize+1;  // 첫 게시물 번호
//		cus.end = 5; // 마지막 게시물 번호
//		String paging = BoardPage.customerfaq(cus.totalCount, cus.pageSize, cus.blockPage, cus.pageNum, request.getRequestURI(),f_type);
//		int start2 = cus.start-1;
//		
//		
//
//		//mav.addObject("list",faqList);
//		mav.setViewName("/customerService/faq");
//		mav.addObject("paging",paging);
//		mav.addObject("start",start2);
//		return mav;
//	}
//	
//	
//	//이거 나중에 업데이트로 이용하셈 
//	@RequestMapping(value = "/customer/getfaqList", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Map<String,Object>> getmap2(@RequestParam Map<String,Object> map) {
//		//System.out.println("+++++++++++++너왔어?"+map);
//		
//		CustomerServiceController cus = new CustomerServiceController();
//		System.out.println(map.get("start"));
//		
//		map.put("start", map.get("start"));
//		
//		System.out.println("+++++++++++++++++++++++++++++++++++++++"+map);
//		List<Map<String,Object>> faqList = service.getFaqList(map);
//		
//		for(Map<String,Object> map2 : faqList) {
//			
//			//고객read
//			String content = ChangeJavanontextarea.change(String.valueOf(map2.get("content")));
//			map2.put("content", content);
//			String title = ChangeJavanontextarea.change(String.valueOf(map2.get("title")));
//			map2.put("title", title);
//			
//			
//		
//		}
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		
//		return faqList;
//	}
//	
//	@RequestMapping(value = "/customer/faqWrite", method = RequestMethod.POST)
//	@ResponseBody
//	public int faqWrite(@RequestParam Map<String,Object> map) {
//		
//		String title = ChangeTEXT.change(String.valueOf(map.get("title")));
//		map.put("title", title);
//		String content = ChangeTEXT.change(String.valueOf(map.get("content")));
//		map.put("content", content);
//		
//		int rs=service.faqWrite(map);
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		
//		return rs;
//	}
//	
//	@RequestMapping(value = "/customer/faqUpdate", method = RequestMethod.POST)
//	@ResponseBody
//	public int faqUpdate(@RequestParam Map<String,Object> map) {
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
//		String title = ChangeTEXT.change(String.valueOf(map.get("title")));
//		map.put("title", title);
//		String content = ChangeTEXT.change(String.valueOf(map.get("content")));
//		map.put("content", content);
//		
//		int rs= service.faqupdate(map);
//		System.out.println("-----------------------------------------------"+rs);
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		return rs;
//	}
//	
//	@RequestMapping(value = "/customer/faqDelete", method = RequestMethod.POST)
//	@ResponseBody
//	public int faqDelete(@RequestParam Map<String,Object> map) {
//		
//		int rs=service.faqDelete(map);
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		return rs;
//	}
//	
//	
//
//	@RequestMapping(value = "/customer/announcementInquiry", method = RequestMethod.POST)
//	@ResponseBody
//	public int announcementInquiryPOST(@RequestBody Map<String,Object> map,HttpServletRequest request) {
//		
//		Map<String,Object> fi = new HashMap<>();
//		map.put("b_title", "(답변 대기중)"+map.get("b_title"));
//		service.insertAnnouncementInquiry(map);
//		//Map<String,Object> customer = service.consultationView(map);
//		//fi.put("ref", map.get("inquiry_num"));
//		//ref 업데이트
//		System.out.println("*******************************************************"+map);
//		int inquiry_num = Integer.parseInt(String.valueOf(map.get("inquiry_num")));
//		System.out.println(inquiry_num);
//		service.updateRefInquiry(map);
//		
//		return inquiry_num;
//	}
//	
//	@RequestMapping(value = "/customer/consultationDetails", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Map<String,Object>> consultationDetailsGET(@RequestParam Map<String,Object> map,HttpSession session,HttpServletRequest request) {
//		List<Map<String,Object>> consultationDetailsList =new ArrayList<>();
//		Map<String,Object> pagingWrap = new HashMap<>();
//		String member_id = null;
//		//아이다 체크\
//		//session.getAttribute("member_id")!=null
//		if(1==1) {
//			map.put("member_id", "hong1");
//			map.put("m_admin", "1");
//			if(map.get("member_id") != null &&map.get("member_id") != "" ) {
//				member_id = String.valueOf(map.get("member_id"));
//			}
//
////			map.put("member_id", session.getAttribute("member_id"));
////			map.put("m_admin", session.getAttribute("m_admin"));
//			CustomerServiceController cus = new CustomerServiceController();
//			
//			
//			Map<String,Object> res = service.selectconsultationDetailsCnt(map);
//			System.out.println("////////////////////////////"+res);
//			String paging=null;
//			
//			
//			if(res.size()!=0) {
//				
//					cus.totalCount = Integer.parseInt(res.get("cnt").toString());
//	//				System.out.println("*******************************"+totalCount);
//	//				System.out.println("*******************************"+res.get("cnt"));
//					cus.pageSize =10;
//					cus.blockPage = 10;
//					cus.totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
//					cus.pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
//					cus.pageTemp = String.valueOf(map.get("pageNum"));
//					if (cus.pageTemp != "null" && !cus.pageTemp.equals(""))
//						cus.pageNum = Integer.parseInt(cus.pageTemp);
//					cus.start = (cus.pageNum - 1) * cus.pageSize+1;  // 첫 게시물 번호
//					cus.end = 10; // 마지막 게시물 번호
//					paging = BoardPage.customerstr(cus.totalCount, cus.pageSize, cus.blockPage, cus.pageNum, request.getRequestURI());
//					int start2 = cus.start-1;
//					
//					map.put("start", start2);
//					map.put("end", cus.end);
//					
//					consultationDetailsList =service.consultationDetails(map);
//	System.out.println(consultationDetailsList);
//					for(Map<String,Object> map2:consultationDetailsList) {
//					
//					String date = map2.get("postdate").toString();
//					String ymd=date.substring(0,10);
//					String ymd2=ymd.replaceAll("-",".");
//					String hms=date.substring(11);
//					String postdate=ymd2+" "+hms;
//					//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
//					String b_content = ChangeJavanontextarea.change(String.valueOf(map2.get("b_content")));
//					map2.put("b_content", b_content);
//					String b_title = ChangeJavanontextarea.change(String.valueOf(map2.get("b_title")));
//					map2.put("b_title", b_title);
//					
//					//리스트가 존재하지않을시
//					if(consultationDetailsList.size()!=0) {
//						map2.put("postdate", postdate);
//						map2.put("cnt", consultationDetailsList.size());
//					}
//				}
//					
//					
//			}
//		}
//		
//		
//		pagingWrap.put("paging", paging);
//		consultationDetailsList.add(pagingWrap);
//		
//		
//		
//		
//		return consultationDetailsList;
//	}
//	
//	@RequestMapping(value = "/customer/consultationView", method = RequestMethod.GET)
//	public List consultationViewGET(@RequestParam Map<String,Object> map,HttpSession session) {
//		System.out.println("너오니너오니너오니너오니너오니너오니너오니너오니너오니너오니너오니");
//		map.put("m_admin",1 );
//		List<Map<String, Object>> consultationView = service.consultationDetails(map);
//		
//				
//		for(Map<String, Object> map2 : consultationView) {
//			String date = map2.get("postdate").toString();
//			String ymd=date.substring(0,10);
//			String ymd2=ymd.replaceAll("-",".");
//			String hms=date.substring(11);
//			String postdate=ymd2+" "+hms;
//			//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
//			String b_content = ChangeJavanontextarea.change(String.valueOf(map2.get("b_content")));
//			map2.put("b_content", b_content);
//			String b_title = ChangeJavanontextarea.change(String.valueOf(map2.get("b_title")));
//			map2.put("b_title", b_title);
//
//			map2.put("postdate", postdate);
//			map2.put("cnt", consultationView.size());
//		}
//				
//		//consultationView.put(boardImg);		
//		
//		
//		return consultationView;
//	}
//	
//	@GetMapping("/customer/inquiryImage")
//	public List<Map<String,Object>> inquiryImage(@RequestParam Map<String,Object> map){
//		List<Map<String,Object>> boardImg = service.selectCustomImg(map);
//		
//		for(Map<String, Object> img : boardImg) {
//			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
//		}
//		//System.out.println("1111111111111111111111111111"+boardImg);
//		
//		return boardImg;
//	}
//	
//	@RequestMapping(value = "/customer/consultationView", method = RequestMethod.POST)
//	public int consultationViewPOST(@RequestBody Map<String,Object> map) {
//		//System.out.println("222222222222222222222222222222222222222222222222222"+map);
//		String b_title = map.get("b_title").toString().replace("(답변 대기중)", "(답변 완료)");
//		map.put("b_title",b_title );
//		int rs = service.insertConsultation(map);
//		service.updateTitleInquiry(map);
//		
//		return rs;
//	}
//	
//	@RequestMapping(value = "/customer/deleteconsultationView", method = RequestMethod.GET)
//	public int  deleteconsultationView(@RequestParam Map<String,Object> map) {
//		ModelAndView mav = new ModelAndView();
//		int rs = service.deleteconsultationView(map);
//		System.out.println("11111111111111111111111111111111111111111rs"+rs);
//		//mav.addObject("member_id",map.get("member_id"));
//		return rs;
//	}
//	

}
