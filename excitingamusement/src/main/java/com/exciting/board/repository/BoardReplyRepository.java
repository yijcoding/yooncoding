package com.exciting.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exciting.dto.BoardReplyDTO;
import com.exciting.entity.BoardReplyEntity;

@Repository
public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity, Integer>{
	@Query("select count(reply_num) as cnt from BoardReplyEntity  where board_id=?1")
	Long boardReplyCnt(int board_id);
}
