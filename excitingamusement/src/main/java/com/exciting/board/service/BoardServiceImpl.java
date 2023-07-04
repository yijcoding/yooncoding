package com.exciting.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.board.repository.BoardFavoriteRepasitory;
import com.exciting.board.repository.BoardImgRepository;
import com.exciting.board.repository.BoardReplyRepository;
import com.exciting.board.repository.BoardRepository;
import com.exciting.dto.BoardDTO;
import com.exciting.dto.BoardFavoriteDTO;
import com.exciting.dto.BoardImgDTO;
import com.exciting.dto.BoardReplyDTO;
import com.exciting.entity.BoardEntity;
import com.exciting.entity.BoardFavoriteEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.entity.BoardReplyEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	BoardRepository boardRepository;
	BoardReplyRepository boardReplyRepository;
	BoardImgRepository boardImgRepository;
	BoardFavoriteRepasitory boardFavoriteRepasitory;

	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository, BoardReplyRepository boardReplyRepository,
			BoardImgRepository boardImgRepository,BoardFavoriteRepasitory boardFavoriteRepasitory) {

		this.boardRepository = boardRepository;
		this.boardReplyRepository = boardReplyRepository;
		this.boardImgRepository = boardImgRepository;
		this.boardFavoriteRepasitory = boardFavoriteRepasitory;
	}

	@Override
	public BoardDTO boardList(int board_id) {
		Optional<BoardEntity> boardOpt = boardRepository.findById(board_id);
		if (boardOpt.isPresent()) {
			return new BoardDTO(boardOpt.get());
		} else {
			return new BoardDTO(boardOpt.get());
		}
	}

	// 게시글 한개정보 가져오기
	@Override
	public BoardDTO boardView(int board_id) {
		Optional<BoardEntity> boardView = boardRepository.findById(board_id);
		if (boardView.isPresent()) {
			return new BoardDTO(boardView.get());
		}
		return null;
	}

	// 게시글의 댓글 갯수
	public Long boardReplyCnt(int board_id) {
		Long boardReplyCnt = boardReplyRepository.boardReplyCnt(board_id);

		return boardReplyCnt;
	}

	// 조회수 업데이트 처리
	@Override
	public void boardVisit(final BoardEntity entity) {
		try {
			final Optional<BoardEntity> original = boardRepository.findById(entity.getBoard_id());

			original.ifPresent(visit -> {
				entity.setVisitcount(entity.getVisitcount() + 1);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//게시판 이미지 불러오기
	@Override
	public List<BoardImgEntity> boardImgSelect(int board_id) {
		return boardImgRepository.findById(board_id);
	}

	
//댓글추가 작업
	@Override
	public void replyInsert(BoardReplyEntity boardReplyEntity) {
		
		//댓글 추가
		
		try {
			BoardReplyEntity savedBoardReplyEntity = boardReplyRepository.save(boardReplyEntity);
			log.info("boardReply 추가"+boardReplyEntity.getMember_id());
			int getReply_num = savedBoardReplyEntity.getReply_num();
			//새로 추가된 글
			Optional<BoardReplyEntity> entity = boardReplyRepository.findById(getReply_num);
			//dto로 변환
			Optional<BoardReplyDTO> dto = entity.map(BoardReplyDTO::new);
			
			// ref갱신
			entity.get().setRef(getReply_num);
			BoardReplyEntity savedDTO = entity.get();
			//업데이트 처리
			boardReplyRepository.save(savedDTO);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ReplyInsert Fail");
		}
		
		
	}

	@Override
	public BoardFavoriteDTO getFavorite(BoardFavoriteEntity boardFavoriteEntity) {
		return boardFavoriteRepasitory.findByBoardId(boardFavoriteEntity.getBoard_id());
	}

//
//	@Override
//	public int boardInsert(Map<String, Object> map) {
//		return repository.boardInsert(map);
//	}
//
//	@Override
//	public Map<String, Object> boardView(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.boardView(map);
//	}
//
//	@Override
//	public int replyInsert(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.replyInsert(map);
//	}
//
//	@Override
//	public List<Map<String, Object>> getComment(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.getComment(map);
//	}
//
//	@Override
//	public int updateBoard(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.updateBoard(map);
//	}
//
//	@Override
//	public int deleteBoard(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.deleteBoard(map);
//	}
//
//	@Override
//	public int boardVisit(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.boardVisit(map);
//	}
//
//	@Override
//	public int replyDelete(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.replyDelete(map);
//	}
//
//	@Override
//	public int replyUpdate(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.replyUpdate(map);
//	}
//
//	@Override
//	public Map<String, Object> boardCnt(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.boardCnt(map);
//	}
//
//	@Override
//	public Map<String, Object> boardReplyCnt(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.boardReplyCnt(map);
//	}
//
//	@Override
//	public int boardImgInsert(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.boardImgInsert(map);
//	}
//
//	@Override
//	public List<Map<String, Object>> boardImgSelect(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.boardImgSelect(map);
//	}
//
//	@Override
//	public int deleteBoardImg(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.deleteBoardImg(map);
//	}
//
//	@Override
//	public Map<String, Object> favoriteBoard(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.favoriteBoard(map);
//	}
//
//	@Override
//	public int intsertFavoriteBoard(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.intsertFavoriteBoard(map);
//	}
//
//	@Override
//	public int deleteFavoriteBoard(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.deleteFavoriteBoard(map);
//	}
//
//	@Override
//	public int re_replyInsert(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return repository.re_replyInsert(map);
//	}

}
