package com.exciting.board.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.BoardDTO;
import com.exciting.dto.BoardFavoriteDTO;
import com.exciting.dto.BoardReplyDTO;
import com.exciting.entity.BoardEntity;
import com.exciting.entity.BoardFavoriteEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.entity.BoardReplyEntity;

public interface BoardService {
//	public List<Map<String, Object>> boardList(Map<String, Object> map);
//	public List<Map<String, Object>> getComment(Map<String, Object> map);
//	public List<Map<String, Object>> boardImgSelect(Map<String, Object> map);
//	public int boardInsert(Map<String,Object> map);
//	public int replyInsert(Map<String,Object> map);
//	public int updateBoard(Map<String,Object> map);
//	public int deleteBoard(Map<String,Object> map);
//	public int boardVisit(Map<String,Object> map);
//	public int intsertFavoriteBoard(Map<String,Object> map);
//	public Map<String, Object> boardView(Map<String,Object> map);
//	public Map<String, Object> boardCnt(Map<String,Object> map);
//	public Map<String, Object> boardReplyCnt(Map<String,Object> map);
//	public Map<String, Object> favoriteBoard(Map<String,Object> map);
//	public int replyDelete(Map<String,Object> map);
//	public int replyUpdate(Map<String,Object> map);
//	public int boardImgInsert(Map<String,Object> map);
//	public int deleteBoardImg(Map<String,Object> map);
//	public int deleteFavoriteBoard(Map<String,Object> map);
//	public int re_replyInsert(Map<String,Object> map);
	BoardDTO boardView(int board_id);
	Long boardReplyCnt(int board_id);
	void boardVisit(final BoardEntity entity);
	List<BoardImgEntity> boardImgSelect(int board_id);
	void replyInsert(final BoardReplyEntity boardReplyEntity);
	void changefavorite(final BoardFavoriteEntity boardFavoriteEntity,int checkData);
	List<BoardReplyEntity> getCommentList(final BoardReplyEntity boardReplyEntity);
	void reReplyInsert(final BoardReplyEntity boardReplyEntity);
	BoardEntity updateBoard(final BoardEntity entity); 
	int commitUpdateBoard(final BoardEntity boardEntity);
}
