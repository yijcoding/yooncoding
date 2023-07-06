package com.exciting.customerService.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exciting.customerService.repository.AnnouncementRepository;
import com.exciting.dto.AnnouncementDTO;
import com.exciting.entity.AnnouncementEntity;

@Service
public class CustomerServiceImpl implements CustomerService {

	private AnnouncementRepository announcementEntiRepository;

	public CustomerServiceImpl(AnnouncementRepository announcementEntiRepository) {
		
		this.announcementEntiRepository = announcementEntiRepository;
		
	}

	public Page<AnnouncementDTO> getAnnouncementList(final AnnouncementEntity entity,int pageNum) {
		Sort sort = Sort.by("postdate").descending();
		
		pageNum = pageNum-1;
		
		PageRequest pageRequest = PageRequest.of(pageNum, 10, sort);
		
		Page<AnnouncementEntity> announcementList = announcementEntiRepository.findAll(pageRequest); 
		
		List<AnnouncementDTO> announcementDTOList = announcementList.getContent().stream().map(AnnouncementDTO::new)
				.collect(Collectors.toList());		
		
		//Pageable pageable = PageRequest.of(pageNum, 10,sort);
		
		Page<AnnouncementDTO> pageList = new PageImpl<>(announcementDTOList, pageRequest, announcementList.getTotalElements());
		
		return pageList;
	}

	
}
