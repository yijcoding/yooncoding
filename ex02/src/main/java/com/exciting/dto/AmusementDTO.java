package com.exciting.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class AmusementDTO {
	private int amuse_id;
	private String a_name;
	private String a_country;
	private String a_time;
	private Date a_opening;
	private int a_view;
	private String a_info;
	private double a_lat;
	private double a_lng;
	private String a_img;
	private String a_note;
	private int review_cnt;
	private float avg_grade;
}
