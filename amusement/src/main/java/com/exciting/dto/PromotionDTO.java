package com.exciting.dto;

import lombok.Data;

@Data
public class PromotionDTO {
	private int promotion_id;
	private int amuse_id;
	private String promotion_content;
	private String promotion_name;
	private String promotion_img;
	private int ticket_id;
}
