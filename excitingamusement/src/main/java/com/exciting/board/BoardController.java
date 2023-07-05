package com.exciting.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exciting.board.repository.BoardRepository;
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
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	private static final String BOARD_UPLOAD_PATH = "src/main/resources/static/upload";
	private static final String Home_BOARD_SAVE_PATH ="C:\\Users\\MOON\\git\\repository2\\ex01\\src\\main\\webapp\\resources\\upload\\" ;
	private static final String BOARD_LOAD_PATH ="src/main/resources/static/upload" ;
	

	
	
	

	@PostMapping("/imageUpload/{board_id}")
	@ResponseBody
	public String createBoard(@RequestParam(value="file",required = false) List<MultipartFile> mf
			,HttpServletRequest request
			,@PathVariable("board_id") int board_id) {
		
		
		Map<String,Object> fi = new HashMap<>();
		
		
		
		try {
			if(mf != null) {
				if(mf.get(0).getOriginalFilename()!=null && !(mf.get(0).getOriginalFilename().equals(""))) {
					
					for(MultipartFile file:mf) {
						String originalFileName = System.currentTimeMillis()+file.getOriginalFilename();
		
						String uploadDir = new ClassPathResource("static/upload/").getFile().getAbsolutePath();
						String safeFile = uploadDir+"/"+originalFileName;
						
						
						fi.put("board_id", board_id);
						fi.put("boardImg", originalFileName);		
						service.boardImgInsert(fi);
						file.transferTo(new File(safeFile));
					}	
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "/board/createBoard";
	}
	
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
	
	/*
	 * 
	 *  게시글 낱개 불러오기 START
	 * 
	 * */
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
			boardImgDTO.stream().map(img->BOARD_LOAD_PATH+img).collect(Collectors.toList());;

			
			//전체 데이터
			BoardDTO boardViewData = service.boardView(boardDTO.getBoard_id());
			
			JSONObject jsonObj = ChangeJson.ToChangeJson(boardViewData);

			jsonObj.put("cnt", boardReplyCnt);
			boardView.add(jsonObj);
			boardView.addAll(boardImg);
			
			//System.out.println(boardView);
			
			
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardView).build();
			
			return ResponseEntity.ok().body(response.getData());
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}

	}
	
	/*
	 * 
	 *  게시글 낱개 불러오기 END
	 * 
	 * */
	
	@GetMapping("/favoriteBoard")
	@ResponseBody
	public ResponseEntity<?> favoriteBoardGet(BoardDTO boardDTO){
		
		try {
			//게시판 추천 ajax뽑아낼 자료
			BoardEntity entity = BoardDTO.toEntity(boardDTO);
			
			BoardDTO favoriteData = service.boardView(entity.getBoard_id());
			
			JSONObject favoriteJSON = ChangeJson.ToChangeJson(favoriteData);
			
			List<JSONObject> responseData = new ArrayList<>();
			
			responseData.add(favoriteJSON);
			
			
			ResponseDTO<BoardDTO> response = ResponseDTO
					.<BoardDTO>builder()
					.data(responseData).build();;
					
			return ResponseEntity.ok().body(response.getData());
		}catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
		
		
		
		
		 
	}
	
	
	@PostMapping("/favoriteBoard")
	@ResponseBody
	public int favoriteBoardPost(@RequestBody BoardFavoriteDTO boardFavoriteDTO){
		//member_id ,board_id 필요
		//추천을 이미 햇는지 검사하는 코드
		System.out.println();
		try {
			BoardFavoriteEntity entity = BoardFavoriteDTO.toEntity(boardFavoriteDTO);
			//db 작업
			service.changefavorite(entity,boardFavoriteDTO.getCheckData());
			return 1;
		} catch (Exception e) {
			return 0;
		}
			
		
	}
	
	
	
/*
 * 
 * 	댓글리스트 출럭 Start
 * 
 * */
	@GetMapping("/replyList")
	@ResponseBody
	public ResponseEntity<?> replyList(BoardReplyDTO boardReplyDTO) {

		try {
			
			BoardReplyEntity boardReplyEntity =  BoardReplyDTO.ToEntity(boardReplyDTO);
			List<BoardReplyEntity> replyList= service.getCommentList(boardReplyEntity);
			List<BoardReplyDTO> replyListDTO = replyList.stream().map(BoardReplyDTO::new).collect(Collectors.toList());
			List<JSONObject> replyListJson = new ArrayList<>();
			for(BoardReplyDTO i :replyListDTO) {
				JSONObject jsonObj = ChangeJson.ToChangeJson(i);
				replyListJson.add(jsonObj);
			}

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().data(replyListJson).build();

			return ResponseEntity.ok().body(response.getData());
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
		
	}

	/*
	 * 
	 * 	댓글리스트 출럭 END
	 * 
	 * */
	
	
	/*
	 * 
	 * 댓글 추가 Start
	 * 
	 * */
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
	
	/*
	 * 
	 * 댓글 추가 END
	 * 
	 * */
	
	/*
	 * 
	 * 대댓글 추가 Start
	 * 
	 * */
	@PostMapping("/insertReReply")
	@ResponseBody
	public void reReplyUpdate(@RequestBody BoardReplyDTO boardReplyDTO){
		System.out.println("reply_insert++++++++++++++++++++++++++++++++++++++"+boardReplyDTO);
		boardReplyDTO.setB_reply(ChangeTEXT.ToJAVA(boardReplyDTO.getB_reply()));
		BoardReplyEntity entity = BoardReplyDTO.ToEntity(boardReplyDTO);
		service.reReplyInsert(entity);
	}
	
	/*
	 * 
	 * 대댓글 추가 END
	 * 
	 * */

	/*
	 * 
	 * Board 게시글 update 수정 GET Start
	 * 
	 * */
	@GetMapping("/updateBoard")
	@ResponseBody
	public ResponseEntity<?> updateBoard(BoardDTO boardDTO){
		
		try {
			BoardEntity entity = BoardDTO.toEntity(boardDTO);
			
			//게시물 정보 가져오기
			BoardEntity BoardDataEntity = service.updateBoard(entity);
			
			BoardDTO BoardDataDTO = new BoardDTO(BoardDataEntity);
			
			//json 파싱
			JSONObject jsonData = ChangeJson.ToChangeJson(BoardDataDTO);
			
			List<BoardImgEntity> imgListEntity = service.boardImgSelect(BoardDataEntity.getBoard_id());
			List<BoardImgDTO> imgList = imgListEntity.stream().map(BoardImgDTO::new).collect(Collectors.toList());
			
			//이미지 각각의 요소 앞에 경로 주입
			imgList.stream().map(img->BOARD_LOAD_PATH+img).collect(Collectors.toList());;
			
			//이미지데이터 같이 전송
			jsonData.put("boardImg", imgList);
			
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().json(jsonData).build();
			
			return ResponseEntity.ok().body(response.getJson());
		} catch (Exception e) {
			String error = e.getMessage();
			
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	/*
	 * 
	 * Board 게시글 update 수정 GET END
	 * 
	 * */

	
	/*
	 * 
	 * Board 게시글 update 수정 POST Start
	 * 
	 * */

	@PostMapping("/updateBoard")
	public int updateBoardpost(@RequestBody BoardDTO boardDTO){
		
	try {
		BoardEntity boardentity  = BoardDTO.toEntity(boardDTO);
		
		service.commitUpdateBoard(boardentity);
			return 1;
	} catch (Exception e) {
		e.printStackTrace();
		return 2;
	}
		
		
	
		
		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+map);
		
	}
	
	
	/*
	 * 
	 * Board 게시글 update 수정 POST END
	 * 
	 * */
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
//	@PostMapping("/board/test")
//	@ResponseBody
//	public void test(@RequestParam Map<String,Object>map, List<MultipartFile> mf) {
//		System.out.println(map);
//		System.out.println(mf);
//		
//		//return "나는 계속 갱신되는데? ㅋ";
//	}
}
