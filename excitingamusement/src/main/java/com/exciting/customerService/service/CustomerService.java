package com.exciting.customerService.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.exciting.dto.AnnouncementDTO;
import com.exciting.entity.AnnouncementEntity;
import com.exciting.entity.BoardImgEntity;

	

public interface CustomerService{
	Page<AnnouncementDTO> getAnnouncementList(final AnnouncementEntity entity,int pageNum,String Search );
	void customerImgInsert(final BoardImgEntity boardImgEntity);
	int insertAnnouncement(final AnnouncementEntity entity);
	AnnouncementEntity getAnnouncementOne(final AnnouncementEntity entity);
	List<BoardImgEntity> getAnnouncementImg(final AnnouncementEntity entity);
}
