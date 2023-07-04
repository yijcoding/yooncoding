package com.exciting.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exciting.entity.BoardImgEntity;

@Repository
public interface BoardImgRepository extends JpaRepository<BoardImgEntity, Integer>{
	@Query("select b from BoardImgEntity b where b.board_id = ?1")
	List<BoardImgEntity> findById(int board_id);
}
