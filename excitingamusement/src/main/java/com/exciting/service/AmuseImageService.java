package com.exciting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exciting.entity.AmuseImage;
import com.exciting.repository.AmuseImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmuseImageService {
	private final AmuseImageRepository amuseImageRepository;
	
	public List<AmuseImage> amuseImgList(Integer amuse_id){
		return this.amuseImageRepository.amuseImgList(amuse_id);
	}
}
