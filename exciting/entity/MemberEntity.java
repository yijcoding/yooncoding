package com.exciting.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(columnNames = "member_id")})
public class MemberEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	
	@Column(name="member_id", nullable = false)
	private String member_id;
	
	private String m_pass;
	private String m_name;
	private String m_email;
	private String m_birth;
	private String m_address;
	private Date m_regidate;
	private String m_phone;
	private String m_gender;
	
	private String m_email1;
	private String m_email2;
	
	private String m_phone1;
	private String m_phone2;
	private String m_phone3;
	
	private String m_year;
	private String m_month;
	private String m_day;
	
	private String m_sha256;
	private String m_enc;
	
	private String m_image;
	
	private int m_admin;
	
	private String m_kakaoId;
	
	private int m_point;

}
