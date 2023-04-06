package org.exiting.board;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.exiting.board.service.BoardService;
import org.exiting.board.util.BoardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Controller
@Data
@Log4j2
public class BoardController {

	@Autowired
	BoardService service;
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/board/board", method = RequestMethod.GET)
	public ModelAndView Board(@RequestParam Map<String,Object> map) {
		ModelAndView mav =new ModelAndView();

		BoardPage boardPage = new BoardPage();
		//paging 처리
		System.out.println("================================================================="+map.get("search"));

		Map<String,Object> res = service.boardCnt(map);
		int totalCount = Integer.parseInt(res.get("cnt").toString());
		int pageSize =30;
		int blockPage = 10;
		int totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
		int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
		String pageTemp = String.valueOf(map.get("pageNum"));
		if (pageTemp != "null" && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);
		int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
		int start2 = start-1;
		Map<String,Object> map2 = new HashMap<>();
		map2.put("start", start2);
		mav.setViewName("/board/Board");
		mav.addObject("startend",map2);


		return mav;
	}
	
	@RequestMapping(value = "/board/boardPaging", method = RequestMethod.GET)
	@ResponseBody
	public String BoardPaging(@RequestParam Map<String,Object> map) {
		
		BoardPage boardPage = new BoardPage();
		String select = String.valueOf(map.get("select"));
		String search = String.valueOf(map.get("search"));
		//paging 처리
		Map<String,Object> res = service.boardCnt(map);
		int totalCount = Integer.parseInt(res.get("cnt").toString());
//		System.out.println("*******************************"+totalCount);
//		System.out.println("*******************************"+res.get("cnt"));
		int pageSize =30;
		int blockPage = 10;
		int totalPage = (int)Math.ceil((double)totalCount / pageSize); // 전체 페이지 수
		int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
		String pageTemp = String.valueOf(map.get("pageNum"));
		if (pageTemp != "null" && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);
		int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
		int end = pageNum * pageSize; // 마지막 게시물 번호
		String paging = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/board/board",search,select);
		int start2 = start-1;
		Map<String,Object> map2 = new HashMap<>();
		map2.put("start", start2);
		
		
		return paging;
	}

	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> BoardList(@RequestParam Map<String,Object> map) {
		List<Map<String,Object>> boardList = service.boardList(map);
		for(Map<String,Object> map2:boardList) {
			String date = map2.get("postdate").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replaceAll("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			map2.put("postdate", postdate);
			map2.put("cnt", boardList.size());
		}
//		System.out.println("================================================================="+boardList.size());
//		System.out.println("================================================================="+boardList);
		return boardList;
	}

	@RequestMapping(value = "board/reply-insert", method = RequestMethod.GET)
	@ResponseBody
	public String reply_insert(@RequestParam Map<String,Object> map) {
		int rs =service.replyInsert(map);
//		System.out.println(rs);
		return String.valueOf(rs);
	}

	@RequestMapping(value = "/board/createBoard", method = RequestMethod.GET)
	public String createBoardGet(Map<String,Object> map,Model model) {
		return "/board/createBoard";
	}
	

	@RequestMapping(value = "/board/createBoard", method = RequestMethod.POST)
	public String createBoardPost(@RequestParam Map<String,Object> map,Model model) throws IOException {
//		List<MultipartFile> files = request.getFiles("files");
//		//이미지 저장경로
//		String uploadDirectory = servletContext.getRealPath("/resources/upload");
//		File uploadDir = new File(uploadDirectory);
//		if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//		
//		for (MultipartFile file : files) {
//            String fileName = file.getOriginalFilename();
//            Path filePath = Paths.get(uploadDirectory, fileName);
//            System.out.println("show your self++++++++++++++++++++++++++++++++++++++++++++++++++++++"+filePath);
//            // 파일 저장
//            Files.write(filePath, file.getBytes());
//        }
//		System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffff"+map.get("file"));
//		System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+map.get("file"));
		String fi = String.valueOf(map.get("file"));
		String file="/resources/upload/"+fi;
		map.put("file", file);
		service.boardInsert(map);
		return "redirect:/board/board";
	}


	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView boardView(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		
		BoardPage boardPage = new BoardPage();
		//paging 처리
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
		int end = pageNum * pageSize; // 마지막 게시물 번호
		int start2 = start-1;
		Map<String,Object> map2 = new HashMap<>();
		map2.put("start", start2);
		map2.put("end", end);
		map2.put("pageNum", pageNum);
		mav.setViewName("/board/Board");
		
		
		Map<String,Object> boardReplyCnt = service.boardReplyCnt(map);
		service.boardVisit(map);
		Map<String,Object> boardView = service.boardView(map); 
		//		for(Map<String, Object> list : boardList) {
		//			System.out.println(list);
		//		}
		System.out.println(boardView);
		mav.setViewName("/board/view");
		mav.addObject("boardView",boardView);
		mav.addObject("startend",map2);
		mav.addObject("boardReplyCnt",boardReplyCnt.get("cnt"));
		return mav;
	}

	@RequestMapping(value = "/board/replyList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> replyList(@RequestParam Map<String,Object> map) {

		List<Map<String,Object>> list= service.getComment(map);
		for(Map<String,Object> map2:list) {
			String date = map2.get("postdate").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replaceAll("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			map2.put("postdate", postdate);

		}
		System.out.println(list);

		return list;
	}

	@RequestMapping(value = "/board/updateBoard", method = RequestMethod.GET)
	public ModelAndView updateBoard(@RequestParam Map<String,Object>map){
		ModelAndView mav = new ModelAndView();
		Map<String, Object> boardList = service.boardView(map); 
		mav.addObject("boardView",boardList);
		mav.setViewName("/board/updateBoard");
		return mav;
	}

	@RequestMapping(value = "/board/updateBoard", method = RequestMethod.POST)
	public ModelAndView updateBoardpost(@RequestParam Map<String,Object>map){
		ModelAndView mav = new ModelAndView();
		String fi = String.valueOf(map.get("file"));
		String file="/resources/upload/"+fi;
		map.put("file", file);
		
		service.updateBoard(map); 
		mav.setViewName("redirect:/board/view?board_id="+map.get("board_id"));
		return mav;
	}

	@RequestMapping(value = "/board/deleteBoard", method = RequestMethod.GET)
	public ModelAndView deleteBoard(@RequestParam Map<String,Object>map){
		ModelAndView mav = new ModelAndView();
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

	@RequestMapping(value = "/board/replyUpdate", method = RequestMethod.GET)
	@ResponseBody
	public int replyUpdate(@RequestParam Map<String,Object>map){
//		System.out.println("111111111111111111111111111111111111"+map);
		int rs = service.replyUpdate(map); 
		return rs;
	}

}
