package com.exciting.login.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exciting.entity.MemberEntity;

public interface LoginRepository extends JpaRepository<MemberEntity, String>{
	
	MemberEntity findByMember_id(String member_id);
	Boolean existsByMember_id(String member_id);

}
