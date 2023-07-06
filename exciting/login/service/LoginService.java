package com.exciting.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.entity.MemberEntity;
import com.exciting.login.persistence.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	public MemberEntity create(final MemberEntity memberEntity) {
		if(memberEntity == null || memberEntity.getMember_id() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String member_id = memberEntity.getMember_id();
		if(loginRepository.existsByMember_id(member_id)) {
			log.warn("member_id already exists {}",member_id);
			throw new RuntimeException("member_id already exists");
		}
		
		return loginRepository.save(memberEntity);
	}
	
	
}
