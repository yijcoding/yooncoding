package com.exciting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exciting.entities.AmusementEntity;
import com.exciting.repository.AmusementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmusementService {
	
	private final AmusementRepository amusementRepository;
	
	//===================국내 목록===================
	public List<AmusementEntity> inAmusementList(){
		return this.amusementRepository.inAmusementList();
	}
	
	public List<AmusementEntity> inAmusementListAvg(){
		return this.amusementRepository.inAmusementListAvg();
	}
	
	public List<AmusementEntity> inAmusementListReview(){
		return this.amusementRepository.inAmusementListReview();
	}
	
	//===================국내 목록===================
	public List<AmusementEntity> outAmusementListAvg(){
		return this.amusementRepository.outAmusementListAvg();
	}
	
	public List<AmusementEntity> outAmusementListReview(){
		return this.amusementRepository.outAmusementListReview();
	}
	
	
	//===================놀이공원 상세===================
	public AmusementEntity getOneAmusement(Integer amuse_id) {
		return this.amusementRepository.getOneAmusement(amuse_id);
	}
}























