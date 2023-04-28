package com.exciting.admin.service;

import java.util.List;
import java.util.Map;

import com.exciting.dto.AimageDTO;
import com.exciting.dto.AmusementDTO;
import com.exciting.dto.MemberDTO;

public interface AdminService {
	public List<MemberDTO> getMemberList(MemberDTO dto,Map<String,Object> map);
	public List<AmusementDTO> amusementList(Map<String,Object> map);
	public Map<String,Object> selectRide(Map<String,Object> map);
	public int memberPowerChange(MemberDTO dto);
	public int adminMemberDelete(MemberDTO dto);
	public int insertAdminAmusement(Map<String,Object> map);
	public int insertAImg(Map<String,Object> map);
	public int adminUpdateImgDelete(AimageDTO aimg_id);
	public int updateAdminAmusementDetail(Map<String,Object>map);
	public int insertRide(Map<String,Object>map);
	public int deleteRide(Map<String,Object>map);
	public int updateRide(Map<String,Object>map);
	public Map<String, Object> selectPromotionId(AmusementDTO amuse_id);
	public List<Map<String,Object>> ridesList(int amuse_id);
	public int deletePromotion(AmusementDTO amuse_id);
	public int deleteFacility(AmusementDTO amuse_id);
	public int deleteAimage(AmusementDTO amuse_id);
	public int deleteSelected(AmusementDTO amuse_id);
	public int deleteRides(AmusementDTO amuse_id);
	public int deleteAmuseReview(AmusementDTO amuse_id);
	public int deleteAmusement(AmusementDTO amuse_id);
	public int deleteTicket(int promotion);
}
