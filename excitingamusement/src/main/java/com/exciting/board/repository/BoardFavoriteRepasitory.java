package com.exciting.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exciting.dto.BoardFavoriteDTO;
import com.exciting.entity.BoardFavoriteEntity;

@Repository
public interface BoardFavoriteRepasitory extends JpaRepository<BoardFavoriteEntity, Integer> {
	@Query("select b from BoardFavoriteEntity b where board_id = ?1")
	BoardFavoriteDTO findByBoardId(int board_id);
}
