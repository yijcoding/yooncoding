package com.exciting.board;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.board.service.BoardService;
import com.exciting.dto.BoardDTO;
import com.exciting.dto.BoardFavoriteDTO;
import com.exciting.dto.BoardImgDTO;
import com.exciting.dto.BoardReplyDTO;
import com.exciting.dto.ResponseDTO;
import com.exciting.entity.BoardEntity;
import com.exciting.entity.BoardFavoriteEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.entity.BoardReplyEntity;
import com.exciting.utils.ChangeJson;
import com.exciting.utils.ChangeTEXT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
//import utils.BoardPage;
//import utils.ChangeJava;
//import utils.ChangeJavanontextarea;
//import utils.ChangeHtml;

@RestController
@Log4j2
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@Autowired
	private static ServletContext servletContext;
	
	private static final String BOARD_SAVE_PATH = "/resources/upload/" ;
	private static final String Home_BOARD_SAVE_PATH ="C:\\Users\\MOON\\git\\repository2\\ex01\\src\\main\\webapp\\resources\\upload\\" ;
	private static final String BOARD_LOAD_PATH ="/resources/upload/" ;
	

	@PostMapping("/reply-insert")
	@ResponseBody
	public ResponseEntity<?> reply_insert(@RequestBody BoardReplyDTO boardReplyDTO) {
		
		try {
		// 문자 치환
		boardReplyDTO.setB_reply(ChangeTEXT.ToJAVA(boardReplyDTO.getB_reply())); 

		BoardReplyEntity entity = BoardReplyDTO.ToEntity(boardReplyDTO);
		
		//댓글 추가 작업 
		service.replyInsert(entity);
		
		return ResponseEntity.ok().body(1);
		}catch(Exception e){
			String error = e.getMessage();
			ResponseDTO<BoardReplyDTO> response = ResponseDTO.<BoardReplyDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	
//
//	@RequestMapping(value = "/board/imageUpload/{board_id}", method = RequestMethod.POST)
//	@ResponseBody
//	public String createBoard(@RequestParam(value="file",required = false) List<MultipartFile> mf
//			,HttpServletRequest request
//			,@PathVariable("board_id") int board_id) {
//		Map<String,Object> fi = new HashMap<>();
//		
//		
//		
//		try {
//			if(mf != null) {
//				if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals(""))) {
//					
//					for(MultipartFile file:mf) {
//						String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
//		
//						String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
//						String safeFile = uploadDir+"/"+originalFileName;
//						
//						
//						fi.put("board_id", board_id);
//						fi.put("boardImg", originalFileName);		
//						service.boardImgInsert(fi);
//						file.transferTo(new File(safeFile));
//					}	
//				}
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		return "/board/createBoard";
//	}
//	
//
//	@RequestMapping(value = "/board/createBoard", method = RequestMethod.POST)
//	public int createBoardPost(@RequestBody Map<String,Object> map,Model model
//			) throws IOException {
//		
//		map.put("member_id","hong1");
//		//이미지 처리를 위한 map
//		
//		System.out.println(map);
//		
//		
//		
//		//insert하고 추가한 튜플의 board_id가져옴
//		service.boardInsert(map);
//		int board_id = Integer.parseInt(String.valueOf(map.get("board_id")));
//		
//		return board_id;
//	}
//	
//	
//	
//
//
	@GetMapping("/view")
	@ResponseBody
	public ResponseEntity<?> boardView(BoardDTO boardDTO) {
		
		
		try {
			List<Map<String,Object>> boardView = new ArrayList<>(); 
			
			//이미지 처리
			List<Map<String,Object>> boardImg = new ArrayList<>();;
			
			BoardEntity boardEntity = BoardDTO.toEntity(boardDTO);
			
			
			Long boardReplyCnt = service.boardReplyCnt(boardDTO.getBoard_id());
			
			//조회수 업데이트 처리
			service.boardVisit(boardEntity);
			
			//게시판 이미지 불러오기
			List<BoardImgEntity> boardImgEntity = service.boardImgSelect(boardDTO.getBoard_id());

			//게시판 이미지 dto로 변환
			List<BoardImgDTO> boardImgDTO = boardImgEntity.stream().map(BoardImgDTO::new).collect(Collectors.toList());
			
			// 이미지 경로 주입	
			for(BoardImgDTO imgs : boardImgDTO) {
				Map<String,Object> map = new HashMap<>();
				map.put("boardImg", BOARD_LOAD_PATH+imgs);
				boardImg.add(map);
			}
			
			//전체 데이터
			BoardDTO boardViewData = service.boardView(boardDTO.getBoard_id());
			
			JSONObject jsonObj = ChangeJson.ToChangeJson(boardViewData);

			jsonObj.put("cnt", boardReplyCnt);
			boardView.add(jsonObj);
			boardView.addAll(boardImg);
			
			System.out.println(boardView);
			
			
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardView).build();
			
			return ResponseEntity.ok().body(response.getData());
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
		
		//boardView.add(boardViewData);
		
		//boardView.put("b_content",ChangeHtml.change(boardView.get("b_content").toString()));
//		mav.setViewName("/board/view");
//		mav.addObject("boardImg", boardImg);
//		mav.addObject("boardView",boardView);
		//mav.addObject("startend",map2);
		
		
	}
//	
	@GetMapping("/favoriteBoard")
	@ResponseBody
	public ResponseEntity<?> favoriteBoardGet(BoardFavoriteDTO boardFavoriteDTO){
		//게시판 추천 ajax뽑아낼 자료
		BoardFavoriteEntity entity = BoardFavoriteDTO.toEntity(boardFavoriteDTO);
		
		BoardFavoriteDTO favoriteData = service.getFavorite(entity);
		
		return data;
	}
//	
//	
//	@RequestMapping(value = "/board/favoriteBoard", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String,Object> favoriteBoardPost(@RequestBody Map<String,Object>map){
//		//member_id ,board_id 필요
//		//추천을 이미 햇는지 검사하는 코드
//		Map<String,Object> data = service.favoriteBoard(map);
//		Map<String,Object> rs = new HashMap<>();
//		
//		System.out.println("***********************************************"+map);
//		
//		
//		String favorite = null;
//		String hate = null;
//		String clientData = null;
//		
//		
//		if(data !=null) {
//			favorite = String.valueOf(data.get("favorite"));
//			hate = String.valueOf(data.get("favorite"));
//			clientData = String.valueOf(map.get("data"));
//		}
//		//추천 클릭시 1이 날라옴
//		//비추 클릭시 2가 날라옴
//		//추천이 있을때 3
//		//비추가 있을때 4
//		if(data ==null) {
//			// 추천이나 비추가 아무것도 안눌렸을때
//			// 추천테이블에 데이터추가
//			// 보드테이블에 추천 및 비추 숫자증가
//			// 넘어가는 키값은 data value값은 1 or 2
//			service.intsertFavoriteBoard(map);
//			service.updateBoard(map);
//		}else if(favorite.equals("1") && clientData.equals("1")) {
//			// 이미 추천이나 비추를 다시 한번 눌렀을때
//			// 보드에 업데이트
//			// 추천테이블에서 삭제
//			map.put("data", 3);
//			service.updateBoard(map);
//			service.deleteFavoriteBoard(map);
//			
//		}else if(hate.equals("2") && clientData.equals("2")) {
//			map.put("data", 4);
//			service.updateBoard(map);
//			service.deleteFavoriteBoard(map);
//		}
//			
//			
//			
//		
//		return rs;
//	}
//	
//	
//	
//
//	@RequestMapping(value = "/board/replyList", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Map<String,Object>> replyList(@RequestParam Map<String,Object> map) {
//
//		
//		System.out.println("22222222222222222222222222"+map);
//		List<Map<String,Object>> list= service.getComment(map);
//		
//		if(list.size()!=0) {
//			for(Map<String,Object> map2:list) {
//				String date = map2.get("postdate").toString();
//				String ymd=date.substring(0,10);
//				String ymd2=ymd.replaceAll("-",".");
//				String hms=date.substring(11);
//				String postdate=ymd2+" "+hms;
//				String b_reply = ChangeJavanontextarea.change(String.valueOf(map2.get("b_reply")));
//				map2.put("b_reply", b_reply);
//				map2.put("postdate", postdate);
//	
//			}
//		}
//		System.out.println(list);
//
//		return list;
//	}
//
//	@RequestMapping(value = "/board/updateBoard", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String,Object> updateBoard(@RequestParam Map<String,Object>map){
//		
//		Map<String, Object> boardList = service.boardView(map); 
//		List<Map<String,Object>> list = service.boardImgSelect(map);
//		
//		for(Map<String,Object> img : list){
//			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
//		}
//		boardList.put("boardImg", list);
//		
//		return boardList;
//	}
//	
//
//
//	@RequestMapping(value = "/board/updateBoard", method = RequestMethod.POST)
//	public int updateBoardpost(@RequestBody Map<String,Object>map,@RequestParam(value="file",required = false) List<MultipartFile> mf
//			,HttpServletRequest request){
//		
//	
//		Map<String,Object> fi = new HashMap<>();
//
//		Map<String,Object> board_id = service.boardView(map);
//		
//		System.out.println("mf+++++++++++++++++++++++++++++++++++++++"+mf);
//		System.out.println("mf+++++++++++++++++++++++++++++++++++++++"+map);
//
//		
//		
//		//이미지 넣기
//		try {
//			if(mf !=null) {
//				if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals("")) ) {
//			
//					for(MultipartFile file:mf) {
//						String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
//						//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+originalFileName);
//						String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
//						String safeFile = uploadDir+"/"+originalFileName;
//						fi.put("board_id", board_id.get("board_id"));
//						//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+safeFile);
//				
//						fi.put("boardImg", originalFileName);		
//						service.boardImgInsert(fi);
//						fi.remove("boardImg");
//						file.transferTo(new File(safeFile));
//						
//					}	
//				}
//			}
//			
//			int rs = service.updateBoard(map); 
//			return rs;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 3;
//		}
//	
//		
//		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+map);
//		
//	}
//	
//	
//	
//	//ajax 이미지 수정
//	@RequestMapping(value = "/board/boardImgShow", method = RequestMethod.POST)
//	@ResponseBody
//	public List<Map<String,Object>> boardImgShow(@RequestParam Map<String,Object>map){
//		List<Map<String,Object>> list = service.boardImgSelect(map);
//		
//		for(Map<String,Object> img : list){
//			img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
//		}
//		
//		return list;
//	}
//
//	
//	@RequestMapping(value = "/board/deleteBoardImg", method = RequestMethod.POST)
//	@ResponseBody
//	public int deleteBoardImg(@RequestBody Map<String,Object>map,HttpServletRequest request){
//		System.out.println("00000000000000000000000000000000000000"+map);
//		List<Map<String, Object>> img = service.boardImgSelect(map);
//		System.out.println(img);
//		
//		for(Map<String,Object> rs : img) {
//			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
//			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
//			if(file.exists()) { // 파일이 존재하면
//				file.delete(); // 파일 삭제	
//			}
//		}
//		
//		int rs = service.deleteBoardImg(map);
//				
//		return rs;
//	}
//	
//	
//	
//	
//	@RequestMapping(value = "/board/deleteBoard", method = RequestMethod.GET)
//	public ModelAndView deleteBoard(@RequestParam Map<String,Object>map,HttpServletRequest request){
//		ModelAndView mav = new ModelAndView();
//		
//		List<Map<String, Object>> img = service.boardImgSelect(map);
//		service.replyDelete(map);
//		//System.out.println("+++++++++++++++++++++++++++++++++++++++++"+map);
//		for(Map<String,Object> rs : img) {
//			//System.out.println("11111111111111111111111111111111111111111"+rs.get("boardImg"));
//			String uploadDir = request.getSession().getServletContext().getRealPath(BOARD_SAVE_PATH);
//			File file =  new File(uploadDir+"/"+rs.get("boardImg"));
//			if(file.exists()) { // 파일이 존재하면
//				file.delete(); // 파일 삭제	
//			}
//		}
//		
//		service.deleteBoardImg(map);
//		service.deleteBoard(map); 
//		
//		mav.setViewName("redirect:/board/board");
//		return mav;
//	}
//
//	@RequestMapping(value = "/board/replyDelete", method = RequestMethod.DELETE)
//	@ResponseBody
//	public int replyDelete(@RequestParam Map<String,Object>map){
//		//System.out.println("reply_delete+++++++"+map);
//		int rs = service.replyDelete(map); 
//		return rs;
//	}
//
//	@RequestMapping(value = "/board/replyUpdate", method = RequestMethod.POST)
//	@ResponseBody
//	public int replyUpdate(@RequestBody Map<String,Object>map){
//		
//		System.out.println("reply_update++++++++++++++++++++++++++++++++++++++"+map);
//		String b_reply = ChangeHtml.change(String.valueOf(map.get("b_reply")));
//		map.put("b_reply", b_reply);
//		int rs = service.replyUpdate(map); 
//		return rs;
//	}
//	
//	@RequestMapping(value = "/board/insertReReply", method = RequestMethod.POST)
//	@ResponseBody
//	public int reReplyUpdate(@RequestBody Map<String,Object>map){
//		System.out.println("reply_insert++++++++++++++++++++++++++++++++++++++"+map);
//		String b_reply = ChangeHtml.change(String.valueOf(map.get("b_reply")));
//		map.put("b_reply", b_reply);
//		int rs = service.re_replyInsert(map); 
//		return rs;
//	}
//
//	
//	@PostMapping("/board/test")
//	@ResponseBody
//	public void test(@RequestParam Map<String,Object>map, List<MultipartFile> mf) {
//		System.out.println(map);
//		System.out.println(mf);
//		
//		//return "나는 계속 갱신되는데? ㅋ";
//	}
}
