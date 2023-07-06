package com.exciting;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import com.exciting.board.repository.BoardFavoriteRepasitory;
import com.exciting.board.repository.BoardReplyRepository;
import com.exciting.board.repository.BoardRepository;
import com.exciting.board.service.BoardServiceImpl;
import com.exciting.dto.BoardDTO;
import com.exciting.dto.BoardFavoriteDTO;
import com.exciting.dto.BoardImgDTO;
import com.exciting.dto.BoardReplyDTO;
import com.exciting.entity.BoardEntity;
import com.exciting.entity.BoardFavoriteEntity;
import com.exciting.entity.BoardReplyEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ExcitingamusementApplicationTests {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private BoardServiceImpl boardService;
	@Autowired
	private BoardReplyRepository boardReplyRepository; 
	@Autowired
	private BoardFavoriteRepasitory boardFavoriteRepasitory;
	
//	@Test
//	void contextLoads() {
//		BoardDTO boardDTO = new BoardDTO();
//		boardDTO.setViewCnt(10);
//		List<BoardEntity> boardAll = boardRepository.findAll();
//		System.out.println(boardAll);
//	}
//	
//	@Test
//	void countTEST() {
//		BoardReplyDTO boardReplyDTO = new BoardReplyDTO();
//		//boardReplyDTO.setViewCnt(10);
//		BoardDTO boardDTO = new BoardDTO();
//		BoardEntity boardEntity = new BoardEntity();
//		Optional<BoardEntity> boardReplyCount = boardRepository.findByBoardId(1519);
//		System.out.println(boardReplyCount);
//		
//		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//		
//		 String jsonStr;
//		try {
//			boardEntity = boardReplyCount.isPresent() ? boardReplyCount.get() : null;
//	        jsonStr = mapper.writeValueAsString(boardEntity);
//	        System.out.println(jsonStr);
//
//	        
//	        JSONParser jsonParser = new JSONParser();
//			try {
//				JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonStr);
//				System.out.println(jsonObj);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (JsonProcessingException e) {
//		    e.printStackTrace();
//		}
//	}
	@Test
	void countTEST() {
		BoardFavoriteDTO dto = new BoardFavoriteDTO();
		//dto.setBoard_id();
		BoardFavoriteDTO favorite = boardFavoriteRepasitory.findByBoardId(1512);
		System.out.println(favorite);
		
//		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//		
//		 String jsonStr;
//		try {
//			boardEntity = boardReplyCount.isPresent() ? boardReplyCount.get() : null;
//	        jsonStr = mapper.writeValueAsString(boardEntity);
//	        System.out.println(jsonStr);
//
//	        
//	        JSONParser jsonParser = new JSONParser();
//			try {
//				JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonStr);
//				System.out.println(jsonObj);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (JsonProcessingException e) {
//		    e.printStackTrace();
//		}
	}
	
	//favoriteTest
	@Test
	public void Favorite() {
		
		BoardFavoriteEntity boardFavoriteEntity = new BoardFavoriteEntity();
		BoardFavoriteDTO dto = new BoardFavoriteDTO();
		boardFavoriteEntity.setBoard_id(1511);
		boardFavoriteEntity.setMember_id("hong1");
		dto.setCheckData(1);
		try {
			final int board_id = boardFavoriteEntity.getBoard_id();
			final String member_id = boardFavoriteEntity.getMember_id();
			
			int checkData = dto.getCheckData();
			
			//비교값 로딩
			Optional<BoardFavoriteEntity> boardEntity = boardFavoriteRepasitory.getFavoriteData(board_id, member_id);
			
			Optional<BoardFavoriteDTO> boardDTO = boardEntity.map(BoardFavoriteDTO::new);
			System.out.println("--------------------------------------------------------------------------------"+boardDTO);
			int favorite = boardDTO.get().getFavorite();
			int hate = boardDTO.get().getHate();
			
			System.out.println(boardEntity.get());
			
			if(checkData ==1) {
				if(favorite==0 && hate == 0) 
					favorite +=1;
				else if(favorite==1 && hate == 0)
					favorite -=1;
				else if(favorite==0 && hate == 1) {
					boardDTO.get().setMessage("좋아요와 싫어요는 하나만 선택가능해요");
					
				}			
			}
			
			if(checkData ==2) {
				if(favorite==0 && hate == 0) 
					hate +=1;
				else if(favorite==0 && hate == 1)
					hate -=1;
				else if(favorite==1 && hate == 0) 
					boardDTO.get().setMessage("좋아요와 싫어요는 하나만 선택가능해요");			
			}
			System.out.println("////////////////////////////////////////////////////////////////////////");
			
			//boardFavoriteRepasitory.findby
			
			boardFavoriteEntity.setFavorite(favorite);
			boardFavoriteEntity.setHate(hate);
			boardFavoriteRepasitory.save(boardFavoriteEntity);
			System.out.println("*********************************************************************");

		}catch(Exception e){
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			throw new RuntimeException("FavoriteUpdate Fail");
		}
		
		
	}

}
