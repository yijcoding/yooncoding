package com.exciting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exciting.entity.Rides;
import com.exciting.repository.RidesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RidesService {
	
	private final RidesRepository ridesRepository;
	
	public List<Rides> ridesListAll(Integer amuse_id){
		return this.ridesRepository.ridesListAll(amuse_id);
	}
	
	public Rides getOneRide(Integer rides_id) {
		return this.ridesRepository.getOneRide(rides_id);
	}
}
