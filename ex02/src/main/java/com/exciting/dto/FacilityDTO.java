package com.exciting.dto;

public class FacilityDTO {
	int facility_id;
	int amuse_id;
	String f_name;
	String f_location;
	String f_info;
	String f_note;
	String f_img;
	
	public FacilityDTO() {
	}
	
	public int getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(int facility_id) {
		this.facility_id = facility_id;
	}
	public int getAmuse_id() {
		return amuse_id;
	}
	public void setAmuse_id(int amuse_id) {
		this.amuse_id = amuse_id;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getF_img() {
		return f_img;
	}
	public void setF_img(String f_img) {
		this.f_img = f_img;
	}

	public String getF_location() {
		return f_location;
	}
	public void setF_location(String f_location) {
		this.f_location = f_location;
	}
	public String getF_info() {
		return f_info;
	}
	public void setF_info(String f_info) {
		this.f_info = f_info;
	}
	public String getF_note() {
		return f_note;
	}
	public void setF_note(String f_note) {
		this.f_note = f_note;
	}
	
	
}
