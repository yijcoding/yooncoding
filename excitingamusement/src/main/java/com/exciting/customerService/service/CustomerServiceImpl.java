package com.exciting.customerService.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exciting.customerService.repository.AnnouncementRepository;
import com.exciting.entity.AnnouncementEntity;

@Service
public class CustomerServiceImpl implements CustomerService {

	private AnnouncementRepository announcementEntiRepository;

	public CustomerServiceImpl(AnnouncementRepository announcementEntiRepository) {
		
		this.announcementEntiRepository = announcementEntiRepository;
		
	}

	public Page<AnnouncementEntity> getAnnouncementList(final AnnouncementEntity entity,int pageNum) {
		Sort sort = Sort.by("postdate").descending();
		
		PageRequest pageRequest = PageRequest.of(pageNum, 10,sort);
		
		Page<AnnouncementEntity> announcementList = announcementEntiRepository.findAll(pageRequest); 
		
		return announcementList;
	}

}
