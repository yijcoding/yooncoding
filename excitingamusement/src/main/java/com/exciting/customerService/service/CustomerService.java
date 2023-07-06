package com.exciting.customerService.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.exciting.entity.AnnouncementEntity;

	

public interface CustomerService{
	Page<AnnouncementEntity> getAnnouncementList(AnnouncementEntity entity,int pageNum );

}
