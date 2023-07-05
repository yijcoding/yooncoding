package com.exciting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exciting.entity.Amusement;
import com.exciting.repository.AmusementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmusementService {
	
	private final AmusementRepository amusementRepository;
	
	//===================국내 목록===================
	public List<Amusement> inAmusementList(){
		return this.amusementRepository.inAmusementList();
	}
	
	public List<Amusement> inAmusementListAvg(){
		return this.amusementRepository.inAmusementListAvg();
	}
	
	public List<Amusement> inAmusementListReview(){
		return this.amusementRepository.inAmusementListReview();
	}
	
	//===================국내 목록===================
	public List<Amusement> outAmusementListAvg(){
		return this.amusementRepository.outAmusementListAvg();
	}
	
	public List<Amusement> outAmusementListReview(){
		return this.amusementRepository.outAmusementListReview();
	}
	
	
	//===================놀이공원 상세===================
	public Amusement getOneAmusement(Integer amuse_id) {
		return this.amusementRepository.getOneAmusement(amuse_id);
	}
}























