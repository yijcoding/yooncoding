package com.exciting.amuse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.AmusementDAO;
import com.exciting.dto.AimageDTO;

@Service
public class AmuseImageServiceImpl implements AmuseImageService{
	@Autowired
	AmusementDAO aDao;
	
	@Override
	public List<AimageDTO> aImgList(Map<String, Object> map) {
		return aDao.aImgList(map);
	}
}
