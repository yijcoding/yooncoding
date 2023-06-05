package com.exciting.login.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exciting.dao.LoginDAO;
import com.exciting.dto.MemberDTO;
import com.exciting.login.service.LoginService;


@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	public LoginController() {
		System.out.println("@Controller 스프링 자동생성");
	}

	//  메인페이지에서 '로그인'링크를 누르면 거쳐가는곳 login.jsp로 이동한다.
	@GetMapping(value = "/login")
	public ModelAndView getLogin(MemberDTO dto,@CookieValue(value="long_login", required=false) String long_login,HttpServletRequest request) {
		System.out.println("/login.get() 접근");

		ModelAndView mav = new ModelAndView();

		//		이전 페이지의 주소가져와서 저장
		String URI = request.getHeader("REFERER"); ;
		System.out.println("URI : "+ URI);

		//		아이디저장유지 체크박스 클릭/언클릭 확인
		System.out.println("/login/get/cookieValue(long_login) : " + long_login);

		if(long_login != null) {
			System.out.println("/login/get/addObject 접근");
			mav.addObject("long_login", long_login);
		}

		//		List<BookDTO> list = bookService.list(dto);
		//		System.out.println("list :" + list);
		//		
		//		mav.addObject("data",list);

		mav.addObject("URI", URI);
		mav.setViewName("login/login");

		return mav;
	}

	//	'로그인'페이지 login.jsp 에서 로그인버튼을 누르면 오는 페이지(이 페이지로 오기전에 LoginApiController에서 등록된아이디인지 검사후, 등록된 아이디이면 온다)
	@PostMapping(value = "/login")
	public ModelAndView postLogin(MemberDTO dto,HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,String> map) {
		System.out.println("/login.post() 접근");

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		//	카카오 아이디로 로그인했는지 안했는지 id 존재여부로 확인. id가 존재하면 kakao_id의 session 생성 id가 존재하지 않으면 기존로그인 절차
		String kakaoId = map.get("kakaoId");
		String nickname = map.get("nickname");
		String thumbnail = map.get("thumbnail");
		

		if(kakaoId != null) {
			System.out.println("/login.post() : kakaoId is not Null");

			session.setAttribute("kakaoId",kakaoId);
			session.setAttribute("nickname",nickname);
			session.setAttribute("thumbnail",thumbnail);
			
//			카카오로그인 아이디와 연동된 member_id가 있는지 확인한다. 연동된 아이디가 없다면 연동된 아이디를 만들수 있게 회원가입 페이지로 이동한다.
			dto.setM_kakaoId(kakaoId);
			String member_id = loginService.kakaoIdCheck(dto);
			System.out.println("/login.post().member_id : "+member_id);
			if(member_id == "" || member_id == null) {
				System.out.println("/login.post() : member is Null");
				mav.setViewName("/login/signUp");
				return mav;
			}else {
				System.out.println("/login.post() : member is not Null");
//				카카오 아이디와 연동된 아이디 새션에 저장
				session.setAttribute("member_id",member_id);
				
//				로그인한 아이디가 관리자용 아이디 인지(1) 아닌지(0) 확인후 session 생성
				dto.setMember_id(member_id);
				MemberDTO adminCheckDTO = loginService.adminCheck(dto);
				System.out.println("/login/post.adminCheckDTO : "+adminCheckDTO);

				int adminNumber = adminCheckDTO.getM_admin();
				if(adminNumber == 0) {
					session.setAttribute("m_admin",0);
				}else if(adminNumber == 1) {
					session.setAttribute("m_admin",1);
				}
				
			}
			
			System.out.println("/login.post().kakaoId :" + kakaoId);
			
//			kakaoid 값이 안넘어왔다면 일반 아이디로 접속한 것이다. 이에 대한 작업 처리
		}else {

			String member_id = dto.getMember_id();
			System.out.println("member_id : "+member_id);

			//		로그인 상태 유지
			session.setAttribute("member_id", member_id);

			//		로그인한 아이디가 관리자용 아이디 인지(1) 아닌지(0) 확인후 session 생성
			MemberDTO adminCheckDTO = loginService.adminCheck(dto);
			System.out.println("/login/post.adminCheckDTO : "+adminCheckDTO);

			int adminNumber = adminCheckDTO.getM_admin();
			if(adminNumber == 0) {
				session.setAttribute("m_admin",0);
			}else if(adminNumber == 1) {
				session.setAttribute("m_admin",1);
			}


			//		아이디 저장(쿠키 생성/삭제)
			String long_login = map.get("long_login");
			System.out.println("/login/post/long_login: "+long_login);
			if(long_login.equals("on")) {
				System.out.println("/login.post()/쿠키생성 진입");
				Cookie cookie = new Cookie("long_login",member_id);
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
			}else if(long_login.equals("off")) {
				System.out.println("/login.post()/쿠키제거 진입");
				Cookie Cookie = new Cookie("long_login", null);
				Cookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
				Cookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.
				response.addCookie(Cookie);
			}
		}

		//		이전페이지의 주소로 보내기
		String URI = map.get("URI");
		if(URI == "" || URI == null) {
			System.out.println("URI NULL 진입");

			mav.setViewName("redirect:/");
		}else {
			System.out.println("URI : "+URI);
			int index = URI.indexOf("8080");
			String U = URI.substring(index+4);
			System.out.println("U : "+ U);

			mav.setViewName("redirect:"+U);
		}


		return mav;

	}

	//	login 페이지에서 '회원가입'링크를 누르면 거쳐가는곳 signUp.jsp로 이동한다.
	@GetMapping(value = "/signUp")
	public ModelAndView searchList(MemberDTO dto) {
		System.out.println("/signUp.get() 접근");

		ModelAndView mav = new ModelAndView();

		mav.setViewName("login/signUp");

		return mav;
	}


	//	signUp.jsp 에서 정보입력후 '회원가입'버튼을 누르면 이동하는곳 회원이 입력한 정보를 member table에 저장하고 메인페이지로 이동한다.
	@PostMapping(value = "/signUp")
	public ModelAndView detail(MemberDTO dto,@RequestParam Map<String,String> map,HttpServletRequest request) {
		System.out.println("/signUp.post() 접근");

		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		String kakaoId = session.getAttribute("kakaoId")+"";
		
		if(kakaoId != null || kakaoId != "") {
			dto.setM_kakaoId(kakaoId);
		}
		
		//		회원가입창에서 사용자가 입력한 값들 정리
		String m_emailfront = map.get("m_emailfront");
		String m_emailselect = map.get("m_emailselect");
		String m_email;
		if(m_emailselect == "") {
			String m_emailback = map.get("m_emailback");
			m_email = m_emailfront + "@" + m_emailback;
		}else {
			m_email = m_emailfront + "@" + m_emailselect;
		}

		dto.setM_email(m_email);

		String year = map.get("year");
		String month = map.get("month");
		String day = map.get("day");

		String m_birth = year+month+day;

		dto.setM_birth(m_birth);

		loginService.insertMember(dto);

		System.out.println("signUp//dto : "+dto);
		
//		회원가입 완료시에 접속되어있는 kakaoId를 로그아웃 시킨다. 다시 접속하게끔
		session.removeAttribute("kakaoId");

		mav.setViewName("login/signUpComplete");

		return mav;
	}

	//	로그아웃 링크를 클릭하면 오는곳
	@GetMapping(value = "/logout")
	public ModelAndView logout(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/logout.get() 접근");

		ModelAndView mav = new ModelAndView();

		//		session 삭제
		HttpSession session = request.getSession();
		session.removeAttribute("member_id");
		session.removeAttribute("kakaoId");

		mav.setViewName("login/login");

		return mav;
	}

	//  회원탈퇴 버튼을 누르면 오는 곳
	@PostMapping(value = "/withdrawal")
	public ModelAndView withdrawal(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/withdrawal.get() 접근");

		ModelAndView mav = new ModelAndView();

		//		세션에서 member_id값 가져와서 dto에 담는다.
		HttpSession session = request.getSession();
		String member_id = session.getAttribute("member_id")+"";
		dto.setMember_id(member_id);

		System.out.println("/withdrawal.get()/member_id : "+member_id);

		//		삭제하려는 member table에 외래키가 많이 연결되있어서 지금은 삭제 못한다.
		loginService.deleteMember(dto);

		mav.setViewName("redirect:/");

		return mav;
	}

	//	login.jsp에서 아이디 찾기 링크를 누르면 오는 곳
	@GetMapping(value = "/searchPw")
	public ModelAndView searchPw(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/searchPw.get() 접근");

		ModelAndView mav = new ModelAndView();



		mav.setViewName("login/searchPw");

		return mav;
	}

	//	비밀번호 찾기 인증성공하고 비밀번호 새로수정하기 페이지버튼 누르면 이동하는곳
	@GetMapping(value = "/newPw")
	public ModelAndView newPwGet(MemberDTO dto) {
		System.out.println("/newPw.get() 접근");

		ModelAndView mav = new ModelAndView();

		//		기존의 id와 pass 가져오기
		MemberDTO d = loginService.selectMember(dto);

		String member_id = d.getMember_id();
		String m_pass = d.getM_pass();

		mav.addObject("member_id",member_id);
		mav.addObject("m_pass",m_pass);
		mav.setViewName("login/newPw");

		return mav;
	}

	//	비밀번호 새로 설정하고 설정하기 버튼누르면 이동하는곳
	@PostMapping(value = "/newPw")
	public ModelAndView newPwPost(MemberDTO dto,HttpServletRequest request) {
		System.out.println("/newPw.post() 접근");

		ModelAndView mav = new ModelAndView();

		//		새로설정된 비밀번호 db에 저장
		loginService.updatePass(dto);

		mav.setViewName("login/newPwComplete");

		return mav;
	}

}
