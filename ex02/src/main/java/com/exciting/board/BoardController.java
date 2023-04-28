package com.exciting.board;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.board.service.BoardService;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import utils.BoardPage;
import utils.ChangeJava;
import utils.ChangeJavanontextarea;
import utils.ChangeHtml;

@Controller
@Data
@Log4j2
public class BoardController {
	
	@Autowired
	BoardService service;
	@Autowired
	private static ServletContext servletContext;
	
	private static final String BOARD_SAVE_PATH = "/resources/upload/" ;
	private static final String Home_BOARD_SAVE_PATH ="C:\\Users\\MOON\\git\\repository2\\ex01\\src\\main\\webapp\\resources\\upload\\" ;
	private static final String BOARD_LOAD_PATH ="/resources/upload/" ;
	
	@RequestMapping(value = "/board/board", method = RequestMethod.GET)
	public ModelAndView Board(@RequestParam Map<String,Object> map) {
		ModelAndView mav =new ModelAndView();

		BoardPage boardPage = new BoardPage();
		//board.jsp에서 설정한 보이는 글 갯수가 없을시 기본적으로 10개가 보임
		int viewCnt=10;
		
		//내가 한번에 볼 글갯수 받아옴
		String cntCheck = String.valueOf(map.get("viewCnt"));
		
		//viewCnt가 null일시 viewCnt 초기값은 10
		if(!(cntCheck.equals("null")) && !(cntCheck.equals(""))) {
			if(Integer.parseInt(cntCheck)!=10) {
				viewCnt= Integer.parseInt(String.valueOf(map.get("viewCnt")));
			
			}
		}
		
		//paging 처리
		Map<String,Object> res = service.boardCnt(map);
		int totalCount = Integer.parseInt(res.get("cnt").toString());
		int pageSize =viewCnt;
		int blockPage = 10;
		int totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
		int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
		String pageTemp = String.valueOf(map.get("pageNum"));
		if (pageTemp != "null" && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);
		int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
		int start2 = start-1;
		int end =viewCnt;
		Map<String,Object> map2 = new HashMap<>();
		map2.put("start", start2);
		map2.put("end", end);
		mav.setViewName("/board/Board");
		mav.addObject("startend",map2);
		


		return mav;
	}
	
	@RequestMapping(value = "/board/boardPaging", method = RequestMethod.GET)
	@ResponseBody
	public String BoardPaging(@RequestParam Map<String,Object> map) throws UnsupportedEncodingException {
		
		BoardPage boardPage = new BoardPage();
		
		//검색페이징을 위한 데이터
		//encoding 이거안쓰면 a태그로 넘어왔을시 한글이 깨짐
		String search = URLEncoder.encode(String.valueOf(map.get("search")), "UTF-8");
		String select = String.valueOf(map.get("select"));
		String b_type = String.valueOf(map.get("b_type"));
		//System.out.println("++++++++++++++++++++++++++++++++++"+search);
		int viewCnt=10;
		//System.out.println("lllllllllllllllllllllllllllllll"+map.get("viewCnt"));
		String cntCheck = String.valueOf(map.get("viewCnt"));
		//System.out.println("lllllllllllllllllllllllllllllll"+cntCheck);
	
		//viewCnt가 null일시 viewCnt 초기값은 10
		if(!(cntCheck.equals("null"))) {
			if(Integer.parseInt(cntCheck)!=10) {
				viewCnt= Integer.parseInt(String.valueOf(map.get("viewCnt")));
			
			}
		}
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+viewCnt);
		
		
		//paging 처리
		Map<String,Object> res = service.boardCnt(map);
		
		//게시글 갯수
		int boardListCnt = res.size();
		
		//검색결과가 0이면 차단 아니면 페이징계산
		if(boardListCnt !=0) {
			
			
			int totalCount = Integer.parseInt(res.get("cnt").toString());
//			System.out.println("*******************************"+totalCount);
//			System.out.println("*******************************"+res.get("cnt"));
			int pageSize =viewCnt;
			int blockPage = 10;
			int totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
			int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
			String pageTemp = String.valueOf(map.get("pageNum"));
			if (pageTemp != "null" && !pageTemp.equals(""))
				pageNum = Integer.parseInt(pageTemp);
			int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
			int end = viewCnt; // 마지막 게시물 번호
			String paging = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/board/board",search,select,b_type);
			int start2 = start-1;
		
			
			return paging;
		}else {
			//게시글아 하나도 없을시 반환
			return "<span class=page-item>1</span>";
		}
		
	}

	
	
	//게시글 목록 ajax를 통해 출력
	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> BoardList(@RequestParam Map<String,Object> map) {
		List<Map<String,Object>> boardList = service.boardList(map);
//		System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqq"+map.get("start"));
//		System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqq"+map.get("end"));
//		System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqq"+ boardList.size());
		
		
		
		//sql시간값 json으로 변환하기 위한 작업
		for(Map<String,Object> map2:boardList) {
			String date = map2.get("postdate").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replaceAll("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
			
			//
			if(boardList.size()!=0) {
				map2.put("postdate", postdate);
			}
		}
		
//		System.out.println("================================================================="+boardList.size());
//		System.out.println("================================================================="+boardList);
		return boardList;
	}

	@RequestMapping(value = "board/reply-insert", method = RequestMethod.GET)
	@ResponseBody
	public String reply_insert(@RequestParam Map<String,Object> map) {
		String b_reply = ChangeHtml.change(String.valueOf(map.get("b_reply")));
		map.put("b_reply", b_reply);
		//mapper에서 if문으로 거르기 위한 임시 데이터
		map.put("refcheck", '1');
		//System.out.println("++++++++++444444444444"+map);
		int rs =service.replyInsert(map);
		//System.out.println("++++++++++444444444444"+map);
		//System.out.println("---------------------------------------------------------"+map);
		service.replyUpdate(map);
//		System.out.println(rs);
		return String.valueOf(rs);
	}

	@RequestMapping(value = "/board/createBoard", method = RequestMethod.GET)
	public String createBoardGet(Map<String,Object> map,Model model) {
		return "/board/createBoard";
	}
	

	@RequestMapping(value = "/board/createBoard", method = RequestMethod.POST)
	public String createBoardPost(@RequestParam Map<String,Object> map,Model model,@RequestParam(value="file",required = false) List<MultipartFile> mf
			,HttpServletRequest request) throws IOException {
		
		//이미지 처리를 위한 map
		Map<String,Object> fi = new HashMap<>();
		
		
		
		//insert하고 추가한 튜플의 board_id가져옴
		service.boardInsert(map);
		
		try {
			if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals(""))) {
				
				for(MultipartFile file:mf) {
					String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
	
					String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
					String safeFile = uploadDir+"/"+originalFileName;
					
					
					fi.put("board_id", map.get("board_id"));
					fi.put("boardImg", originalFileName);		
					service.boardImgInsert(fi);
					file.transferTo(new File(safeFile));
				}	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/board";
	}


	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView boardView(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		//String path ="session.getServletContext().getRealPath(\"/\")";
		BoardPage boardPage = new BoardPage();
//		paging 처리
		Map<String,Object> res = service.boardCnt(map);
		
		
		int totalCount = Integer.parseInt(res.get("cnt").toString());
		int pageSize =10;
		int blockPage = 10;
		int totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
		int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
		String pageTemp = String.valueOf(map.get("pageNum"));
		if (pageTemp != "null" && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);
		int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
		int end = 10; // 마지막 게시물 번호
		int start2 = start-1;
		
		
		Map<String,Object> map2 = new HashMap<>();
		map2.put("start", start2);
		map2.put("end", end);
		map2.put("pageNum", pageNum);
		mav.setViewName("/board/Board");
		
		
		
		
		Map<String,Object> boardReplyCnt = service.boardReplyCnt(map);
		//조회수 업데이트
		service.boardVisit(map);
		Map<String,Object> boardView = service.boardView(map);
		List<Map<String,Object>> boardImg = service.boardImgSelect(map);
		
		
		
		for(Map<String, Object> img : boardImg) {
			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
		}
		
		
		int cntSize = Integer.parseInt(String.valueOf(boardReplyCnt.get("cnt"))); 
		if( cntSize != 0){
			mav.addObject("boardReplyCnt",boardReplyCnt.get("cnt"));
		}else {
			mav.addObject("boardReplyCnt","0");
		}
		
		
		mav.setViewName("/board/view");
		mav.addObject("boardImg", boardImg);
		mav.addObject("boardView",boardView);
		mav.addObject("startend",map2);
		
		return mav;
	}
	
	@RequestMapping(value = "/board/favoriteBoard", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> favoriteBoardGet(@RequestParam Map<String,Object>map){
		//게시판 추천 ajax뽑아낼 자료
		Map<String,Object> data = service.boardView(map);
		
		return data;
	}
	
	
	@RequestMapping(value = "/board/favoriteBoard", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> favoriteBoardPost(@RequestParam Map<String,Object>map){
		//member_id ,board_id 필요
		//추천을 이미 햇는지 검사하는 코드
		Map<String,Object> data = service.favoriteBoard(map);
		Map<String,Object> rs = new HashMap<>();
		
		System.out.println("***********************************************"+map);
		
		
		String favorite = null;
		String hate = null;
		String clientData = null;
		
		
		if(data !=null) {
			favorite = String.valueOf(data.get("favorite"));
			hate = String.valueOf(data.get("favorite"));
			clientData = String.valueOf(map.get("data"));
		}
		//추천 클릭시 1이 날라옴
		//비추 클릭시 2가 날라옴
		//추천이 있을때 3
		//비추가 있을때 4
		if(data ==null) {
			// 추천이나 비추가 아무것도 안눌렸을때
			// 추천테이블에 데이터추가
			// 보드테이블에 추천 및 비추 숫자증가
			// 넘어가는 키값은 data value값은 1 or 2
			service.intsertFavoriteBoard(map);
			service.updateBoard(map);
		}else if(favorite.equals("1") && clientData.equals("1")) {
			// 이미 추천이나 비추를 다시 한번 눌렀을때
			// 보드에 업데이트
			// 추천테이블에서 삭제
			map.put("data", 3);
			service.updateBoard(map);
			service.deleteFavoriteBoard(map);
			
		}else if(hate.equals("2") && clientData.equals("2")) {
			map.put("data", 4);
			service.updateBoard(map);
			service.deleteFavoriteBoard(map);
		}
			
			
			
		
		return rs;
	}
	
	
	

	@RequestMapping(value = "/board/replyList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> replyList(@RequestParam Map<String,Object> map) {
		System.out.println("ajaxpagingCheck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+map);
		List<Map<String,Object>> list= service.getComment(map);
		System.out.println("listCheck111111111111111111111111111111111111"+list);
		int commentListCount = Integer.parseInt(String.valueOf(list.get(0).get("cnt")));
		if(commentListCount != 0) {
			
			
			int totalCount = commentListCount;
//			System.out.println("*******************************"+totalCount);
//			System.out.println("*******************************"+res.get("cnt"));
			int pageSize =10;
			int blockPage = 10;
			int totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
			int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
			System.out.println("ajaxpagingCheck22222222222222222222!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			String pageTemp = String.valueOf(map.get("pageNum"));
			if (pageTemp != "null" && !pageTemp.equals(""))
				pageNum = Integer.parseInt(pageTemp);
			int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
			int end = 10; // 마지막 게시물 번호
			String paging = BoardPage.AjaxCommentPaging(totalCount, pageSize, blockPage, pageNum);
			int start2 = start-1;
		
			map.put("start",start2);
			map.put("end",end);
			
			map.put("commentPagingCheck", "1");
			
			list= service.getComment(map);
			
			if(list.size()!=0) {
				for(Map<String,Object> map2:list) {
					String date = map2.get("postdate").toString();
					String ymd=date.substring(0,10);
					String ymd2=ymd.replaceAll("-",".");
					String hms=date.substring(11);
					String postdate=ymd2+" "+hms;
					String b_reply = ChangeJavanontextarea.change(String.valueOf(map2.get("b_reply")));
					map2.put("b_reply", b_reply);
					map2.put("postdate", postdate);
					
		
				}
				list.get(0).put("paging",paging);
			}
		}		
		
	
		//System.out.println(list);

		return list;
	}

	@RequestMapping(value = "/board/updateBoard", method = RequestMethod.GET)
	public ModelAndView updateBoard(@RequestParam Map<String,Object>map){
		ModelAndView mav = new ModelAndView();
		Map<String, Object> boardList = service.boardView(map); 
		List<Map<String,Object>> list = service.boardImgSelect(map);
		
		for(Map<String,Object> img : list){
			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
		}
		mav.addObject("boardView",boardList);
		mav.addObject("boardImg",list);
		mav.setViewName("/board/updateBoard");
		return mav;
	}
	


	@RequestMapping(value = "/board/updateBoard", method = RequestMethod.POST)
	public ModelAndView updateBoardpost(@RequestParam Map<String,Object>map,@RequestParam(value="file",required = false) List<MultipartFile> mf
			,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
	
		Map<String,Object> fi = new HashMap<>();

		Map<String,Object> board_id = service.boardView(map);
		
		//System.out.println("mf+++++++++++++++++++++++++++++++++++++++"+mf.get(0).getOriginalFilename());

		
		
		//이미지 넣기
		try {
			if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals("")) ) {
		
				for(MultipartFile file:mf) {
					String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+originalFileName);
					String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
					String safeFile = uploadDir+"/"+originalFileName;
					fi.put("board_id", board_id.get("board_id"));
					//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+safeFile);
			
					fi.put("boardImg", originalFileName);		
					service.boardImgInsert(fi);
					fi.remove("boardImg");
					file.transferTo(new File(safeFile));
					
				}	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+map);
		service.updateBoard(map); 
		mav.setViewName("redirect:/board/view?board_id="+map.get("board_id"));
		return mav;
	}
	
	
	
	//ajax 이미지 수정
	@RequestMapping(value = "/board/boardImgShow", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> boardImgShow(@RequestParam Map<String,Object>map){
		List<Map<String,Object>> list = service.boardImgSelect(map);
		
		for(Map<String,Object> img : list){
			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
		}
		
		return list;
	}

	
	@RequestMapping(value = "/board/deleteBoardImg", method = RequestMethod.POST)
	@ResponseBody
	public int deleteBoardImg(@RequestParam Map<String,Object>map,HttpServletRequest request){
		System.out.println(map);
		List<Map<String, Object>> img = service.boardImgSelect(map);
		System.out.println(img);
		
		for(Map<String,Object> rs : img) {
			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
			if(file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제	
			}
		}
		
		int rs = service.deleteBoardImg(map);
				
		return rs;
	}
	
	
	
	
	@RequestMapping(value = "/board/deleteBoard", method = RequestMethod.GET)
	public ModelAndView deleteBoard(@RequestParam Map<String,Object>map,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> img = service.boardImgSelect(map);
		service.replyDelete(map);
		for(Map<String,Object> rs : img) {
			//System.out.println("11111111111111111111111111111111111111111"+rs.get("boardImg"));
			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
			if(file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제	
			}
		}
		
		service.deleteBoardImg(map);
		service.deleteBoard(map); 
		
		mav.setViewName("redirect:/board/board");
		return mav;
	}

	@RequestMapping(value = "/board/replyDelete", method = RequestMethod.GET)
	@ResponseBody
	public int replyDelete(@RequestParam Map<String,Object>map){
		int rs = service.replyDelete(map); 
		return rs;
	}

	@RequestMapping(value = "/board/replyUpdate", method = RequestMethod.POST)
	@ResponseBody
	public int replyUpdate(@RequestParam Map<String,Object>map){
		//System.out.println("reply_update++++++++++++++++++++++++++++++++++++++"+map);
		String b_reply = ChangeHtml.change(String.valueOf(map.get("b_reply")));
		map.put("b_reply", b_reply);
		int rs = service.replyUpdate(map); 
		return rs;
	}
	
	@RequestMapping(value = "/board/insertReReply", method = RequestMethod.POST)
	@ResponseBody
	public int reReplyUpdate(@RequestParam Map<String,Object>map){
		System.out.println("reply_update++++++++++++++++++++++++++++++++++++++"+map.get("reply_num"));
		String b_reply = ChangeHtml.change(String.valueOf(map.get("b_reply")));
		map.put("b_reply", b_reply);
		int rs = service.re_replyInsert(map); 
		return rs;
	}

}
