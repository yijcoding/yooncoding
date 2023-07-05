package com.exciting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exciting.entity.Facility;
import com.exciting.repository.FacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityService {
	private final FacilityRepository facilityRepository;
	
	public List<Facility> facAllList(Integer amuse_id){
		return this.facilityRepository.facAllList(amuse_id);
	}
}
