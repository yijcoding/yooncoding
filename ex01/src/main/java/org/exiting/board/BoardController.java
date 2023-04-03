package org.exiting.board;

import java.util.List;
import java.util.Map;

import org.exiting.board.service.BoardService;
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
	public String Board(Model model) {
		
		return "/board/Board";
	}
	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> BoardList(@RequestParam Map<String,Object> map) {
		System.out.println(map);
		List<Map<String,Object>> boardList = service.boardList(map); 
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
		System.out.println(map);
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
		System.out.println(map);
		service.boardInsert(map);
		return "redirect:board/board";
	}
	
	
	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView boardView(@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String,Object> boardView = service.boardView(map); 
//		for(Map<String, Object> list : boardList) {
//			System.out.println(list);
//		}
		System.out.println(boardView);
		mav.setViewName("/board/view");
		mav.addObject("boardView",boardView);
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
}
