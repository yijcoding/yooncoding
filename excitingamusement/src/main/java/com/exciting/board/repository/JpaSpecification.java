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
	
	
	public static Specification<?> LikeSearch(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.like(root.get("c_title"), "%" + searchValue + "%");
            }
        };
    }
	
//	
}
