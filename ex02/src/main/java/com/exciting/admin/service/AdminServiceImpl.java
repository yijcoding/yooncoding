package com.exciting.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dao.AdminDAO;
import com.exciting.dto.AimageDTO;
import com.exciting.dto.AmusementDTO;
import com.exciting.dto.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO dao;

	@Override
	public List<MemberDTO> getMemberList(MemberDTO dto,Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.getMemberList(dto,map);
	}

	@Override
	public int memberPowerChange(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.memberPowerChange(dto);
	}
	
	public Map<String, Object> selectPromotionId(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.selectPromotionId(amuse_id);
	}

	@Override
	public int adminMemberDelete(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.adminMemberDelete(dto);
	}

	@Override
	public List<AmusementDTO> amusementList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.amusementList(map);
	}
	
	@Override
	public int insertAdminAmusement(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.insertAdminAmusement(map);
	}

	@Override
	public int insertAImg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertAImg(map);
	}

	@Override
	public int adminUpdateImgDelete(AimageDTO aimg_id) {
		// TODO Auto-generated method stub
		return dao.adminUpdateImgDelete(aimg_id);
	}

	@Override
	public int updateAdminAmusementDetail(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.updateAdminAmusementDetail(map);
	}

	@Override
	public int deletePromotion(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.deletePromotion(amuse_id);
	}

	@Override
	public int deleteFacility(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.deleteFacility(amuse_id);
	}

	@Override
	public int deleteAimage(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.deleteAimage(amuse_id);
	}
	
	@Override
	public int deleteSelected(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.deleteSelected(amuse_id);
	}
	
	@Override
	public int deleteRides(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.deleteRides(amuse_id);
	}
	
	@Override
	public int deleteAmuseReview(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.deleteAmuseReview(amuse_id);
	}
	
	@Override
	public int deleteAmusement(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return dao.deleteAmusement(amuse_id);
	}

	@Override
	public int deleteTicket(int promotion) {
		// TODO Auto-generated method stub
		return dao.deleteTicket(promotion);
	}

	@Override
	public List<Map<String,Object>> ridesList(int amuse_id) {
		// TODO Auto-generated method stub
		return dao.ridesList(amuse_id);
	}

	@Override
	public int insertRide(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertRide(map);
	}

	@Override
	public int deleteRide(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.deleteRide(map);
	}

	@Override
	public Map<String, Object> selectRide(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectRide(map);
	}

	@Override
	public int updateRide(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.updateRide(map);
	}
	
}
