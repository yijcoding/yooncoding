package com.exciting.dto;

public class MypageDTO {
	
//	회원 아이디
	String member_id;
//	어느 페이지의 댓글인가
	String category;
//	글을 쓴것인가(1) 글의 댓글을 작성한것인가(2).
	String depth;
	String b_title;
	String b_content;
	String postdate;
	String b_type;
	String view_id;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	public String getB_type() {
		return b_type;
	}
	public void setB_type(String b_type) {
		this.b_type = b_type;
	}
	
	public String getView_id() {
		return view_id;
	}
	public void setView_id(String view_id) {
		this.view_id = view_id;
	}
	
	
	@Override
	public String toString() {
		return "MypageDTO [member_id=" + member_id + ", category=" + category + ", depth=" + depth + ", b_title="
				+ b_title + ", b_content=" + b_content + ", postdate=" + postdate + ", b_type=" + b_type + ", view_id="
				+ view_id + "]";
	}
	
	
}
