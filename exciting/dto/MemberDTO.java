package com.exciting.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
	
	String id;
	
	String member_id;
	String m_pass;
	String m_name;
	String m_email;
	String m_birth;
	String m_address;
	Date m_regidate;
	String m_phone;
	String m_gender;
	
	String m_email1;
	String m_email2;
	
	String m_phone1;
	String m_phone2;
	String m_phone3;
	
	String m_year;
	String m_month;
	String m_day;
	
	String m_sha256;
	String m_enc;
	
	String m_image;
	
	int m_admin;
	
	String m_kakaoId;
	
	int m_point;
}
