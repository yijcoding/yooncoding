package com.exciting.dto;

public class RidesDTO {
	int rides_id;
	int amuse_id;
	String r_name;
	String r_location;
	String r_info;
	int r_min_height;
	int r_max_height;
	String r_note;
	String r_img;
	String r_video;
	
	public RidesDTO() {
	}
	
	public String getR_img() {
		return r_img;
	}
	public void setR_img(String r_img) {
		this.r_img = r_img;
	}
	public int getRides_id() {
		return rides_id;
	}
	public void setRides_id(int rides_id) {
		this.rides_id = rides_id;
	}
	public int getAmuse_id() {
		return amuse_id;
	}
	public void setAmuse_id(int amuse_id) {
		this.amuse_id = amuse_id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_location() {
		return r_location;
	}
	public void setR_location(String r_location) {
		this.r_location = r_location;
	}
	public String getR_info() {
		return r_info;
	}
	public void setR_info(String r_info) {
		this.r_info = r_info;
	}
	public int getR_min_height() {
		return r_min_height;
	}
	public void setR_min_height(int r_min_height) {
		this.r_min_height = r_min_height;
	}
	public int getR_max_height() {
		return r_max_height;
	}
	public void setR_max_height(int r_max_height) {
		this.r_max_height = r_max_height;
	}
	public String getR_note() {
		return r_note;
	}
	public void setR_note(String r_note) {
		this.r_note = r_note;
	}
	public String getR_video() {
		return r_video;
	}
	public void setR_video(String r_video) {
		this.r_video = r_video;
	}

	@Override
	public String toString() {
		return "RidesDTO [rides_id=" + rides_id + ", amuse_id=" + amuse_id + ", r_name=" + r_name + ", r_location="
				+ r_location + ", r_info=" + r_info + ", r_min_height=" + r_min_height + ", r_max_height="
				+ r_max_height + ", r_note=" + r_note + "]";
	}
}
