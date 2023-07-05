package com.exciting.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.board.repository.BoardFavoriteRepasitory;
import com.exciting.board.repository.BoardImgRepository;
import com.exciting.board.repository.BoardReplyRepository;
import com.exciting.board.repository.BoardRepository;
import com.exciting.dto.BoardDTO;
import com.exciting.dto.BoardFavoriteDTO;
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
			BoardImgRepository boardImgRepository, BoardFavoriteRepasitory boardFavoriteRepasitory) {

		this.boardRepository = boardRepository;
		this.boardReplyRepository = boardReplyRepository;
		this.boardImgRepository = boardImgRepository;
		this.boardFavoriteRepasitory = boardFavoriteRepasitory;
	}


	/*
	 * 
	 * 게시글 낱개 정보 GET Start
	 * 
	 * */ 
	@Override
	public BoardDTO boardView(int board_id) {
		Optional<BoardEntity> boardView = boardRepository.findById(board_id);
		if (boardView.isPresent()) {
			return new BoardDTO(boardView.get());
		}
		return null;
	}

	/*
	 * 
	 * 게시글 낱개 정보 GET END
	 * 
	 * */ 
	
	/*
	 * 
	 *  게시글 수정
	 * 	updateBoard
	 *  GET Start
	 * 
	 * */ 
	

	public BoardEntity updateBoard(final BoardEntity boardEntity) {
		BoardEntity entity = new BoardEntity();
		try {
			Optional<BoardEntity> boardData = boardRepository.findByBoardId(boardEntity.getBoard_id());
			
			if(boardData.isPresent()) {
				entity = boardData.get();
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return entity;
		
	}
	
	/*
	 * 
	 *  게시글 수정
	 * 	updateBoard
	 *  GET END
	 * 
	 * */ 
	
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
	public void replyInsert(final BoardReplyEntity boardReplyEntity) {

		// 댓글 추가

		try {
			BoardReplyEntity savedBoardReplyEntity = boardReplyRepository.save(boardReplyEntity);
			log.info("boardReply 추가" + boardReplyEntity.getMember_id());
			int getReply_num = savedBoardReplyEntity.getReply_num();
			// 새로 추가된 글
			Optional<BoardReplyEntity> entity = boardReplyRepository.findById(getReply_num);
			// dto로 변환
			Optional<BoardReplyDTO> dto = entity.map(BoardReplyDTO::new);

			// ref갱신
			entity.get().setRef(getReply_num);
			entity.get().setPostdate(LocalDateTime.now());
			BoardReplyEntity savedDTO = entity.get();
			// 업데이트 처리
			boardReplyRepository.save(savedDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ReplyInsert Fail");
		}

	}

	
	/*
	 * 
	 * 좋아요 변환작업
	 *
	 */
	
	
	@Override
	public void changefavorite(final BoardFavoriteEntity boardFavoriteEntity, int checkData) {

		// 조건문 사용을 위한 선언
		// Specification<BoardFavoriteEntity> spec =
		// Specification.where(JpaSpecification.equalFavoriteDataId(boardFavoriteEntity.getBoard_id()));

		try {
			int board_id = boardFavoriteEntity.getBoard_id();
			String member_id = boardFavoriteEntity.getMember_id();

			// 비교값 로딩
			Optional<BoardFavoriteEntity> favoriteEntity = boardFavoriteRepasitory.getFavoriteData(board_id, member_id);
		
			favoriteEntity.filter(entity -> entity.getFavorite_num() != 0).ifPresentOrElse(entity -> {
				favoriteWork(favoriteEntity.get(), checkData, boardFavoriteEntity);
			}, () -> {
				BoardFavoriteEntity entity = boardFavoriteRepasitory.save(boardFavoriteEntity);

				favoriteWork(entity, checkData, boardFavoriteEntity);

			});

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("FavoriteUpdate Fail");
		}
		
	}

	public void favoriteWork(BoardFavoriteEntity BoardFavoriteEntity, int checkData,
			BoardFavoriteEntity boardFavoriteEntity) {
		
		
		try{
			BoardFavoriteDTO favoriteDTO = new BoardFavoriteDTO(BoardFavoriteEntity);
			
			Optional<BoardEntity> boardEntityData = boardRepository.findByBoardId(favoriteDTO.getBoard_id());
			BoardDTO boardDTO = new BoardDTO(boardEntityData.get());
			
			
			
			// if(boardDTO.get().getFavorite())
			int favorite = favoriteDTO.getFavorite();
			int hate = favoriteDTO.getHate();
			int boardFavorite = boardDTO.getFavorite();
			int boardHate = boardDTO.getHate();
			
			// 버튼이벤트 좋아요 작업
			if (checkData == 1) {
				if (favorite == 0 && hate == 0) {				
					favorite += 1;
					boardDTO.setFavorite(boardFavorite+1);
				}
				else if (favorite == 1 && hate == 0) {
					favorite -= 1;
					boardDTO.setFavorite(boardFavorite-1);				
				}
				else if (favorite == 0 && hate == 1) {
					favoriteDTO.setMessage("좋아요와 싫어요는 하나만 선택가능해요");
				}
				
			}

			// 버튼이벤트 싫어요 작업
			if (checkData == 2) {
				if (favorite == 0 && hate == 0) {
					hate += 1;
					boardDTO.setHate(boardHate+1);				
				}
				else if (favorite == 0 && hate == 1) {
					hate -= 1;
					boardDTO.setHate(boardHate-1);			
				}
				else if (favorite == 1 && hate == 0)
					favoriteDTO.setMessage("좋아요와 싫어요는 하나만 선택가능해요");
				
			}
			
			// favoriteBoard에 넣을 값 적재
			favoriteDTO.setFavorite(favorite);
			favoriteDTO.setHate(hate);
			
			//FavoiteBoard DTO -> Entity
			BoardFavoriteEntity favoriteEntity = BoardFavoriteDTO.toEntity(favoriteDTO);
			boardFavoriteRepasitory.save(favoriteEntity);
			
			//Board DTO -> Entity
			BoardEntity boardEntity = BoardDTO.toEntity(boardDTO);
			boardRepository.save(boardEntity);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("favoriteWork Error");
		}
		//Entity -> DTO
		

		
	}
	/*
	 * 
	 * favorite END
	 * 
	 * */
	

	
	/*
	 * 
	 * 댓글 리스트 출력 Start
	 * 
	 * */
	
	
	@Override
	public List<BoardReplyEntity> getCommentList(BoardReplyEntity boardReplyEntity) {
		
		return boardReplyRepository.getCommentList(boardReplyEntity.getBoard_id());
	}
	
	
	/*
	 * 
	 * 댓글 리스트 출력 END
	 * 
	 * */
	
	/*
	 * 
	 * 대댓글 추가 Start
	 * 
	 * */
	
	@Override
	public void reReplyInsert(final BoardReplyEntity boardReplyEntity) {
		try {
			int seq = boardReplyRepository.replyData(boardReplyEntity.getRef());

			boardReplyEntity.setSeq(seq + 1);
			
			boardReplyEntity.setPostdate(LocalDateTime.now());
			
			boardReplyRepository.save(boardReplyEntity);		
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("reReplyInsert Error");
		}
		
	}

	/*
	 * 
	 * 대댓글 추가 END
	 * 
	 * */

	

	/*
	 * 
	 * Board 게시글 update 수정 POST Start
	 * 
	 * */
	@Override
	public int commitUpdateBoard(BoardEntity boardEntity) {
		try {
			
			Optional<BoardEntity> originBoardData = boardRepository.findByBoardId(boardEntity.getBoard_id());
			
			if(originBoardData.isPresent()) {
				BoardEntity origin = originBoardData.get();
				origin.setB_content(boardEntity.getB_content());
				origin.setB_title(boardEntity.getB_title());
				origin.setB_type(boardEntity.getB_type());
				
				boardRepository.save(origin);
			}else {
				
				throw new RuntimeException("originBoardData Not Found");
			}
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardUpdateCommit Error");
		}
	};
	

	/*
	 * 
	 * Board 게시글 update 수정 POST End
	 * 
	 * */
	
	
	
}
