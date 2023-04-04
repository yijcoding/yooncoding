package org.exiting.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exiting.board.service.BoardService;
import org.exiting.board.util.BoardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Controller
@Data
@Log4j2
public class BoardController {

	@Autowired
	BoardService service;

	@RequestMapping(value = "/board/board", method = RequestMethod.GET)
	public ModelAndView Board(@RequestParam Map<String,Object> map) {
		ModelAndView mav =new ModelAndView();

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
		String paging = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/board/board");
		int start2 = start-1;
		Map<String,Object> map2 = new HashMap<>();
		map2.put("start", start2);
		map2.put("end", end);
		mav.setViewName("/board/Board");
		mav.addObject("paging",paging);
		mav.addObject("startend",map2);


		return mav;
	}

	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> BoardList(@RequestParam Map<String,Object> map) {
		List<Map<String,Object>> boardList = service.boardList(map);

		Map<String,Object> replycnt = new HashMap<>();
		for(Map<String,Object> map2:boardList) {
			String date = map2.get("postdate").toString();
			String ymd=date.substring(0,10);
			String ymd2=ymd.replaceAll("-",".");
			String hms=date.substring(11);
			String postdate=ymd2+" "+hms;
			map2.put("postdate", postdate);

		}
		return boardList;
	}

	@RequestMapping(value = "board/reply-insert", method = RequestMethod.GET)
	@ResponseBody
	public String reply_insert(@RequestParam Map<String,Object> map) {
		int rs =service.replyInsert(map);
		System.out.println(rs);
		return String.valueOf(rs);
	}

	@RequestMapping(value = "/board/createBoard", method = RequestMethod.GET)
	public String createBoardGet(Map<String,Object> map,Model model) {
		return "/board/createBoard";
	}


	@RequestMapping(value = "/board/createBoard", method = RequestMethod.POST)
	public String createBoardPost(@RequestParam Map<String,Object> map,Model model) {
		service.boardInsert(map);
		return "redirect:board/board";
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
		String paging = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "/board/board");
		int start2 = start-1;
		Map<String,Object> map2 = new HashMap<>();
		map2.put("start", start2);
		map2.put("end", end);
		map2.put("pageNum", pageNum);
		mav.setViewName("/board/Board");
		
		
		
		service.boardVisit(map);
		Map<String,Object> boardView = service.boardView(map); 
		//		for(Map<String, Object> list : boardList) {
		//			System.out.println(list);
		//		}
		System.out.println(boardView);
		mav.setViewName("/board/view");
		mav.addObject("boardView",boardView);
		mav.addObject("paging",paging);
		mav.addObject("startend",map2);
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
		System.out.println("111111111111111111111111111111111111"+map);
		int rs = service.replyUpdate(map); 
		return rs;
	}

}
