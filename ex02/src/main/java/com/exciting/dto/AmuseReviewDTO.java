package com.exciting.dto;

public class AmuseReviewDTO {
	int review_id;
	int amuse_id;
	String member_id;
	String r_content;
	int r_grade;
	String r_regidate;
	int rownum;
	int ref;
	int level;
	int seq;
	
	public AmuseReviewDTO() {
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getAmuse_id() {
		return amuse_id;
	}

	public void setAmuse_id(int amuse_id) {
		this.amuse_id = amuse_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public int getR_grade() {
		return r_grade;
	}

	public void setR_grade(int r_grade) {
		this.r_grade = r_grade;
	}
	
	public String getR_regidate() {
		return r_regidate;
	}
	public void setR_regidate(String r_regidate) {
		this.r_regidate = r_regidate;
	}
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "AmuseReviewDTO [review_id=" + review_id + ", amuse_id=" + amuse_id + ", member_id=" + member_id
				+ ", r_content=" + r_content + ", r_grade=" + r_grade + ", r_regidate=" + r_regidate + ", rownum="
				+ rownum + ", ref=" + ref + ", level=" + level + ", seq=" + seq + "]";
	}

}
