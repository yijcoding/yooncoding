package com.exciting;

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

@SpringBootTest
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

}
