package com.exciting.board.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.exciting.dto.BoardFavoriteDTO;
import com.exciting.entity.BoardFavoriteEntity;
import com.exciting.entity.BoardImgEntity;

public class JpaSpecification {
	public static Specification<?> equalImgData(int board_id) {
        return new Specification<BoardImgEntity>() {
			@Override
            public Predicate toPredicate(Root<BoardImgEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 1) equal
					
                return criteriaBuilder.equal(root.get("board_id"), board_id);
            }
        };
    }
	
//	public static Specification<BoardFavoriteEntity> equalFavoriteDataMember(String member_id) {
//        return new Specification<BoardFavoriteEntity>() {
//			@Override
//            public Predicate toPredicate(Root<BoardFavoriteEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                // 1) equal
//                return criteriaBuilder.equal(root.get("member_id"), member_id);
//            }
//        };
//    }
}
