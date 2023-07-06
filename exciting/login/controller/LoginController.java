package com.exciting.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exciting.dto.MemberDTO;
import com.exciting.dto.ResponseDTO;
import com.exciting.entity.MemberEntity;
import com.exciting.login.security.TokenProvider;
import com.exciting.login.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody MemberDTO memberDTO){
		
		if(memberDTO == null || memberDTO.getM_pass() == null) {
			throw new RuntimeException("invalid Password value.");
		}
		
		try {
//		요청을 이용해 저장할 유저 만들기
		MemberEntity user = MemberEntity.builder()
				.member_id(memberDTO.getMember_id())
				.m_pass(passwordEncoder.encode(memberDTO.getM_pass()))
				.build();
		
//		서비스를 이용해 리포지터리에 유저 저장(저장한값 UserEntity 형태로 반환)
		MemberEntity registeredUser = loginService.create(user);
		
//		반환된 UserEntity에서 값 꺼낸후 UserDTO에 저장
		MemberDTO responseUserDTO = MemberDTO.builder()
				.id(registeredUser.getId())
				.member_id(registeredUser.getMember_id())
				.build();
		
		return ResponseEntity.ok().body(responseUserDTO);
		}catch (Exception e) {
			ResponseDTO<?> responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity
					.badRequest()
					.body(responseDTO);
		}
		
	}
}
