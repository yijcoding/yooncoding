package com.exciting.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.admin.service.AdminService;
import com.exciting.amuse.service.AmuseImageService;
import com.exciting.dto.AimageDTO;
import com.exciting.dto.AmusementDTO;
import com.exciting.dto.MemberDTO;

import utils.BoardPage;

@RestController
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@Autowired
	AmuseImageService amuseImageService;
	
	@GetMapping("/admin")
	public ModelAndView admin(MemberDTO dto,ModelAndView mav,HttpSession session) {
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			
			
			if(adminCheck == 1 ) {

				mav.setViewName("/admin/adminMain");
			}else {
				mav.setViewName("redirect:/");
			} 
		
		
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	
	@GetMapping("/adminMember")
	public ModelAndView adminMemeberGET
	(@RequestParam Map<String,Object> map,MemberDTO dto
			,ModelAndView mav,HttpSession session) {
		
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			
			if(adminCheck == 1 ) {
				String search="";
				String searchCheck=String.valueOf(map.get("search"));
				
				if(!(searchCheck.equals("null"))) {
					System.out.println("너 여기 들어오냐");
					search=searchCheck;
				}
				
				map.put("search",search);
				
					
				List<MemberDTO> memberList = service.getMemberList(dto,map);
			
//				System.out.println("*******************************"+totalCount);
//				System.out.println("*******************************"+res.get("cnt"));
				int totalCount = memberList.size();
				int pageSize =10;
				int blockPage = 10;
				int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
				String pageTemp = String.valueOf(map.get("pageNum"));
				if (pageTemp != "null" && !pageTemp.equals(""))
				pageNum = Integer.parseInt(pageTemp);
				int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
				int end = 10; // 마지막 게시물 번호	
				int start2 = start-1;
				System.out.println(start2);
				map.put("start", start2);
				map.put("end", end);
				
				String paging = BoardPage.adminMemberPagingStr(totalCount, pageSize, blockPage, pageNum,"/adminMember",search);
				
				memberList = service.getMemberList(dto,map);
				
				mav.addObject("paging",paging);
				mav.addObject("search",search);
				mav.addObject("member",memberList);
				mav.setViewName("/admin/adminMember");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	@PostMapping("/adminMember")
	public ModelAndView adminMemeberPOST(MemberDTO dto,ModelAndView mav,HttpSession session) {
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			if(adminCheck == 1 ) {
				service.memberPowerChange(dto);
				mav.setViewName("redirect:/adminMember");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	@GetMapping("/adminMemberDelete")
	public ModelAndView adminMemberDeleteGET(MemberDTO dto,ModelAndView mav,HttpSession session) {
		//System.out.println("00000000000000000000000000"+dto);
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			if(adminCheck == 1 ) {
				service.adminMemberDelete(dto);
				//mav.setViewName("redirect:/adminMember");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	@GetMapping("/adminAmusement")
	public ModelAndView adminAmusementGET(AmusementDTO dto,ModelAndView mav
			,HttpSession session,@RequestParam Map<String,Object> map) {
		
		//System.out.println("00000000000000000000000000"+dto);
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			if(adminCheck == 1 ) {
				String search="";
				String searchCheck=String.valueOf(map.get("search"));
				
				if(!(searchCheck.equals("null"))) {
					System.out.println("너 여기 들어오냐");
					search=searchCheck;
				}
				
				map.put("search",search);
				
				List<AmusementDTO> amusementList = service.amusementList(map);
				
				int totalCount = amusementList.size();
				int pageSize =10;
				int blockPage = 10;
				int pageNum = 1; // 바꿔가면서 테스트 1~10 =>1, 11~20 => 11
				String pageTemp = String.valueOf(map.get("pageNum"));
				if (pageTemp != "null" && !pageTemp.equals(""))
				pageNum = Integer.parseInt(pageTemp);
				int start = (pageNum - 1) * pageSize+1;  // 첫 게시물 번호
				int end = 9; // 마지막 게시물 번호	
				int start2 = start-1;
				map.put("start", start2);
				map.put("end", end);
				
				String paging = BoardPage.adminMemberPagingStr(totalCount, pageSize, blockPage, pageNum,"/adminAmusement",search);
				
				amusementList = service.amusementList(map);
				
				mav.addObject("paging",paging);
				mav.addObject("search",search);
				mav.addObject("amusement",amusementList);
				mav.setViewName("/admin/adminAmusement");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	
	
	@GetMapping("/insertAdminAmusement")
	public ModelAndView insertAdminAmusementGET(ModelAndView mav,HttpSession session) {
		
		//System.out.println("00000000000000000000000000"+dto);
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			if(adminCheck == 1 ) {

				mav.setViewName("/admin/adminInsertAmusememt");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	@PostMapping("/insertAdminAmusement")
	public ModelAndView insertAdminAmusementPOST(ModelAndView mav,HttpSession session
			,@RequestParam Map<String,Object> map) {
		
		//System.out.println("00000000000000000000000000"+dto);
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			System.out.println("ddddddddddddddddddddddddddd"+map.get("imgs"));
						if(adminCheck == 1 ) {
				Map<String,Object> aImg = new HashMap<>();
				//System.out.println(map);
				String imgs[] =null;
				if(map.get("imgs")!="") {
					String a_img = String.valueOf(map.get("imgs"));
					imgs = a_img.split(",");
					map.put("a_img", imgs[0]);
				}
				
				service.insertAdminAmusement(map);
				
				//System.out.println("11111111111111111111111111111"+map);
				if(map.get("imgs")!="") {
					for(String img : imgs) {
						aImg.put("url", img);
						aImg.put("amuse_id", map.get("amuse_id"));
						service.insertAImg(aImg);
						aImg.clear();
					}
				
				}
				
				mav.setViewName("redirect:/adminAmusement");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	@GetMapping("/adminAmusementDetail")
	public ModelAndView adminAmusementDetailGET(ModelAndView mav,HttpSession session
			,@RequestParam Map<String,Object> map) {
		
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			if(adminCheck == 1 ) {
				int amuse_id = Integer.parseInt(String.valueOf(map.get("amuse_id")));
				List<AmusementDTO> amusementDetail = service.amusementList(map);
				List<AimageDTO> aImgList = amuseImageService.aImgList(map);
				List<Map<String,Object>> ridesList =  service.ridesList(amuse_id);
				mav.addObject("rides",ridesList);
				mav.addObject("amuse",amusementDetail);
				mav.addObject("amuseimg",aImgList);
				mav.setViewName("/admin/adminAmusementDetail");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
	}
	
	@GetMapping("/updateAmusmentDetail")
	public ModelAndView updateAmusmentDetailGET(ModelAndView mav,HttpSession session
			,@RequestParam Map<String,Object> map) {
		
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
			if(adminCheck == 1 ) {
				List<AmusementDTO> amusementDetail = service.amusementList(map);
				List<AimageDTO> aImgList = this.amuseImageService.aImgList(map);
				mav.addObject("amuse",amusementDetail);
				mav.addObject("amuseimg",aImgList);
				mav.setViewName("/admin/updateAmusmentDetail");
			}else {
				mav.setViewName("redirect:/");
			} 
		}else {
			mav.setViewName("redirect:/");
		}
		
		return mav;
		
		
	}
	
	@GetMapping("/updateAmusmentDetailGetImg")
	public List<AimageDTO> updateAmusmentDetailImgGET(ModelAndView mav,HttpSession session
			,@RequestParam Map<String,Object> map) {
		
		int amuse_id = Integer.parseInt(String.valueOf(map.get("amuse_id")));
		List<AimageDTO> aImgList = this.amuseImageService.aImgList(map);
		
		return aImgList;
	}
	
	
	@PostMapping("/updateAmusmentDetail")
	public ModelAndView updateAmusmentDetailPOST(ModelAndView mav,HttpSession session
			,@RequestParam Map<String,Object> map) {
		
		//System.out.println("00000000000000000000000000"+dto);
		if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
			int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
				if(adminCheck == 1 ) {
				Map<String,Object> aImg = new HashMap<>();
				System.out.println(map);
				String imgs[] =null;
					if(map.get("imgs")!="") {
						String a_img = String.valueOf(map.get("imgs"));
						imgs = a_img.split(",");
						map.put("a_img", imgs[0]);
					}
					service.updateAdminAmusementDetail(map);
					
					System.out.println("11111111111111111111111111111"+map);
					if(map.get("imgs")!="") {
						for(String img : imgs) {
							aImg.put("url", img);
							aImg.put("amuse_id", map.get("amuse_id"));
							service.insertAImg(aImg);
							aImg.clear();
						}
					
					}
					mav.setViewName("redirect:/adminAmusement");
					
				}else {
					mav.setViewName("redirect:/");
				}
		
		
		}
		return mav;
	}
	
	
	//나중에 확정날떄
		@PostMapping("/updateAmusmentDetailImgDelete")
		public int updateAmusmentDetailImgDeletePOST(ModelAndView mav,HttpSession session
				,AimageDTO aimg_id) {
			int rs =0;
			if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
				rs =service.adminUpdateImgDelete(aimg_id);
			}else {
				mav.setViewName("redirect:/");
			}
			
			return rs;
		}
		
		@GetMapping("/deleteAmusment")
		public ModelAndView deleteAmusment(ModelAndView mav,HttpSession session
				,AmusementDTO amuse_id) {
			if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
				//티켓삭제
				//먼저 프로모션 조회
				Map<String,Object> promotion = service.selectPromotionId(amuse_id);
				System.out.println("dddddddddddddddddddddddddd"+promotion);
				//티켓삭제
				String promotionNullCheck =String.valueOf(promotion);
				boolean b =promotionNullCheck=="null";
				System.out.println(b);
				
				if(promotionNullCheck!="null") {
					System.out.println("너 여기 왓니 111111111111111111");
					int promotion_id = Integer.parseInt(promotionNullCheck);
					//티켓 삭제
					service.deleteTicket(promotion_id);
					 
				}
				//프로모션 삭제
				service.deletePromotion(amuse_id);
				System.out.println("11111111111111111111111111");
				//facility (편의시설 삭제)
				service.deleteFacility(amuse_id);
				System.out.println("22222222222222222222222222");
				//aimage 삭제
				service.deleteAimage(amuse_id);
				//selectd 삭제 (member랑 fk 맺어져있음)
				service.deleteSelected(amuse_id);
				//rides 삭제(놀이기구)
				service.deleteRides(amuse_id);
				//rimage(놀이기구 사진)
				service.deleteAmuseReview(amuse_id);
				//놀이공원 삭제
				service.deleteAmusement(amuse_id);
				mav.setViewName("redirect:/adminAmusement");
			}else {
				mav.setViewName("redirect:/");
			}
			
			return mav;
			
		}
		
		@GetMapping("/insertRide")
		public ModelAndView insertRideGet(ModelAndView mav,HttpSession session
				,AmusementDTO amuse_id) {
			if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
				
				mav.setViewName("/admin/adminInsertRide");
			}else {
				mav.setViewName("redirect:/");
			}
			
			return mav;
			
		}
		
		@PostMapping("/insertRide")
		public ModelAndView insertRidePost(ModelAndView mav,HttpSession session
				,@RequestParam Map<String,Object> map) {
			if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
				System.out.println(map);
				
				Map<String,Object> rImg = new HashMap<>();
				
				if(map.get("imgs")!="") {
					map.put("r_img", map.get("imgs"));
				}
				
				
				//System.out.println("11111111111111111111111111111"+map);

				service.insertRide(map);
				mav.setViewName("redirect:/adminAmusementDetail?amuse_id="+map.get("amuse_id"));
			}else {
				mav.setViewName("redirect:/");
			}
			
			return mav;
			
		}
		
		@GetMapping("/deleteRide")
		public ModelAndView deleteRideGet(ModelAndView mav,HttpSession session
				,@RequestParam Map<String,Object> map) {
			if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
				System.out.println("1111111111111111111111111111111111111"+map);
				service.deleteRide(map);
				
				mav.setViewName("redirect:/adminAmusementDetail?amuse_id="+map.get("amuse_id"));
			}else {
				mav.setViewName("redirect:/");
			}
			
			return mav;
			
		}
		
		@GetMapping("/updateRide")
		public ModelAndView updateRideGET(ModelAndView mav,HttpSession session
				,@RequestParam Map<String,Object> map) {
			if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
				System.out.println("오긴왔냐");
				Map<String,Object> rideOne = service.selectRide(map);
				mav.addObject("ride",rideOne);
				mav.setViewName("/admin/updateRide");
			}else {
				mav.setViewName("redirect:/");
			}
			
			return mav;
			
		}
		
		@PostMapping("/updateRide")
		public ModelAndView updateRidePOST(ModelAndView mav,HttpSession session
				,@RequestParam Map<String,Object> map) {
			
			//System.out.println("00000000000000000000000000"+dto);
			if(!(String.valueOf(session.getAttribute("m_admin")).equals("null"))) {
				int adminCheck = Integer.parseInt(String.valueOf(session.getAttribute("m_admin")));
					if(adminCheck == 1 ) {
					Map<String,Object> aImg = new HashMap<>();
					System.out.println(map);
						if(map.get("imgs")!="") {
							String a_img = String.valueOf(map.get("imgs"));
							map.put("a_img", a_img);
						}
						service.updateRide(map);
						
						mav.setViewName("redirect:/adminAmusement");
						
					}else {
						mav.setViewName("redirect:/");
					}
			
			
			}
			return mav;
		}
}
