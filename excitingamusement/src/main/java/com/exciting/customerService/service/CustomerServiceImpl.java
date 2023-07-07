package com.exciting.customerService.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.exciting.board.repository.BoardImgRepository;
import com.exciting.board.repository.JpaSpecification;
import com.exciting.customerService.repository.AnnouncementRepository;
import com.exciting.dto.AnnouncementDTO;
import com.exciting.entity.AnnouncementEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.utils.ChangeJson;

@Service
public class CustomerServiceImpl implements CustomerService {

	 private AnnouncementRepository announcementRepository;
	 private BoardImgRepository boardImgRepository;

	  @Autowired
	  public CustomerServiceImpl(AnnouncementRepository announcementRepository, BoardImgRepository boardImgRepository) {
	    this.announcementRepository = announcementRepository;
	    this.boardImgRepository = boardImgRepository;
	  }

	/*
	 * 
	 * 공지사항리스트 AnnouncementList 출력 show Start
	 * 
	 * */
	public Page<AnnouncementDTO> getAnnouncementList(final AnnouncementEntity entity,int pageNum,String search) {
		Sort sort = Sort.by("postdate").descending();
		
		pageNum = pageNum-1;
		
		PageRequest pageRequest = PageRequest.of(pageNum, 10, sort);
		Specification<AnnouncementEntity> spec = (Specification<AnnouncementEntity>) JpaSpecification.LikeSearch(search);
		
		Page<AnnouncementEntity> announcementList = announcementRepository.findAll(spec,pageRequest); 
		System.out.println(announcementList);
		List<AnnouncementDTO> announcementDTOList = announcementList.getContent().stream().map(AnnouncementDTO::new)
				.collect(Collectors.toList());		
		
		//Pageable pageable = PageRequest.of(pageNum, 10,sort);
		
		Page<AnnouncementDTO> pageList = new PageImpl<>(announcementDTOList, pageRequest, announcementList.getTotalElements());
		
		return pageList;
	}
	
	/*
	 * 
	 * 공지사항리스트 AnnouncementList 출력 show END
	 * 
	 * */
	
	/*
	 * 
	 * 공지사항이미지저장 AnnouncementImage announcementimg insert Start
	 * 
	 * */
	
	public void customerImgInsert(final BoardImgEntity boardImgEntity) {
		
		try {
			Integer announcement_num = boardImgEntity.getAnnouncement_num();
			Integer inquiry_num = boardImgEntity.getInquiry_num();
			
			if(announcement_num != null ||announcement_num!= 0 )
				boardImgRepository.save(boardImgEntity);
			else if(inquiry_num != null ||inquiry_num!= 0 )
				boardImgRepository.save(boardImgEntity);
				
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("customerImgInsert Service Error");
		}
		
	}
	
	/*
	 * 
	 * 공지사항이미지저장 AnnouncementImage announcementimg insert END
	 * 
	 * */
	
	/*
	 * 
	 * 공지사항글쓰기 Announcement Write insert Start
	 * 
	 * */
	public int insertAnnouncement(final AnnouncementEntity entity) {
		
		AnnouncementEntity insertEntity = announcementRepository.save(entity);
		
		int announcement_num = insertEntity.getAnnouncement_num();
		
		return announcement_num;
	}

	/*
	 * 
	 * 공지사항글쓰기 Announcement Write insert END
	 * 
	 * */
	
	/*
	 * 
	 * 공지사항 세부 announcement Detail Start
	 * 
	 */
	
	public AnnouncementEntity getAnnouncementOne(final AnnouncementEntity entity) {
	
		
		Optional<AnnouncementEntity> data = null;
			
		int announcement_num = entity.getAnnouncement_num();
		try {
			data = announcementRepository.findById(announcement_num);
			
			JSONObject dataJson = ChangeJson.ToChangeJson(data.get());
			
			if(data.isPresent()) {
				return data.get();
			}else {
				throw new RuntimeException("getAnnouncementOne is Empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getAnnouncementOne Error");
		}
		
		
		
	}
	
	/*
	 * 
	 * 공지사항 세부 announcement Detail END
	 * 
	 */
	
	/*
	 * 
	 * 공지사항 이미지 불러오기 announcement Image img GET Start
	 * 
	 */
	
	public List<BoardImgEntity> getAnnouncementImg(final AnnouncementEntity entity){
		
		List<BoardImgEntity> boardImgList = null;
		List<String> boardImgData = null;
		int announcement_num = entity.getAnnouncement_num();
		try {
			
			boardImgList = boardImgRepository.findByAnnouncement(announcement_num);
			
			return boardImgList;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getAnnouncementOne Error");
		}
	}
	
	/*
	 * 
	 * 공지사항 이미지 불러오기 announcement Image img GET END
	 * 
	 */
}
