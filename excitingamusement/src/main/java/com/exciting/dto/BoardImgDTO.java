package com.exciting.dto;

import com.exciting.entity.BoardImgEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardImgDTO {
	private int boardImg_num;
	private int board_id;
	private String boardImg;
	private int announcement_num;
	private int inquiry_num;
	
	
	public BoardImgDTO(final BoardImgEntity entity) {
	    super();
	    this.boardImg_num = entity.getBoardImg_num();
	    this.board_id = entity.getBoard_id();
	    this.boardImg = entity.getBoardImg();
	    this.announcement_num = entity.getAnnouncement_num();
	    this.inquiry_num = entity.getInquiry_num();
	}
	
	public static BoardImgEntity toEntity(BoardImgDTO dto) {
	    return BoardImgEntity.builder()
	            .boardImg_num(dto.getBoardImg_num())
	            .board_id(dto.getBoard_id())
	            .boardImg(dto.getBoardImg())
	            .announcement_num(dto.getAnnouncement_num())
	            .inquiry_num(dto.getInquiry_num())
	            .build();
	}
	
	
}
