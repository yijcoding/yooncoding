package com.exciting.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exciting.dto.AimageDTO;
import com.exciting.dto.AmusementDTO;
import com.exciting.dto.MemberDTO;

@Repository
public class AdminDAO {
	
	@Autowired
	SqlSessionTemplate sql;
	
	public List<MemberDTO> getMemberList(MemberDTO dto,Map<String,Object> map) {
		map.put("member_id", dto.getMember_id());
		map.put("m_admin", dto.getM_admin());
		
		return sql.selectList("admin.getMemberList",map);
	}
	
	public List<AmusementDTO> amusementList(Map<String,Object> map) {
		
		return sql.selectList("admin.amusementList",map);
	}
	
	public List<Map<String,Object>> ridesList(int amuse_id) {
		
		return sql.selectList("admin.ridesList",amuse_id);
	}
	
	public Map<String,Object> selectRide(Map<String,Object> map) {
		
		return sql.selectOne("admin.selectRide",map);
	}
	
	public int insertAdminAmusement(Map<String,Object> map) {
		return sql.insert("admin.insertAdminAmusement",map);
	}
	
	public Map<String, Object> selectPromotionId(AmusementDTO amuse_id) {
		// TODO Auto-generated method stub
		return sql.selectOne("admin.selectPromotionId",amuse_id);
	}
	
	public int insertAImg(Map<String,Object> map) {
		
		return sql.insert("admin.insertAImg",map);
	}
	
	public int memberPowerChange(MemberDTO dto) {
		// TODO Auto-generated constructor stub
		return sql.update("admin.memberPowerChange",dto);
	}
	
	public int updateAdminAmusementDetail(Map<String,Object>map) {
		// TODO Auto-generated constructor stub
		return sql.update("admin.updateAdminAmusementDetail",map);
	}
	
	public int insertRide(Map<String,Object>map) {
		// TODO Auto-generated constructor stub
		return sql.update("admin.insertRide",map);
	}
	
	public int updateRide(Map<String,Object>map) {
		// TODO Auto-generated constructor stub
		return sql.update("admin.updateRide",map);
	}
	
	public int adminMemberDelete(MemberDTO dto) {
		// TODO Auto-generated constructor stub
		return sql.delete("admin.adminMemberDelete",dto);
	}
	
	public int adminUpdateImgDelete(AimageDTO aimg_id) {
		return sql.delete("admin.adminUpdateImgDelete",aimg_id);
	}
	
	public int deletePromotion(AmusementDTO amuse_id) {
		return sql.delete("admin.deletePromotion",amuse_id);
	}
	
	public int deleteFacility(AmusementDTO amuse_id) {
		return sql.delete("admin.deleteFacility",amuse_id);
	}
	
	public int deleteAimage(AmusementDTO amuse_id) {
		return sql.delete("admin.deleteAimage",amuse_id);
	}
	
	public int deleteSelected(AmusementDTO amuse_id) {
		return sql.delete("admin.deleteSelected",amuse_id);
	}
	
	public int deleteRides(AmusementDTO amuse_id) {
		return sql.delete("admin.deleteRides",amuse_id);
	}
	
	public int deleteAmuseReview(AmusementDTO amuse_id) {
		return sql.delete("admin.deleteAmuseReview",amuse_id);
	}
	
	public int deleteAmusement(AmusementDTO amuse_id) {
		return sql.delete("admin.deleteAmusement",amuse_id);
	}
	
	public int deleteTicket(int promotion) {
		return sql.delete("admin.deleteTicket",promotion);
	}
	
	public int deleteRide(Map<String,Object> map) {
		return sql.delete("admin.deleteRide",map);
	}
	
}
