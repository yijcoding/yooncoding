package com.exciting.login.controller;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exciting.dto.MemberDTO;
import com.exciting.login.service.LoginService;

import utils.Gmail;
import utils.Sha256;

@RestController
public class LoginAjaxController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/idCheck")
	public int idCheck(MemberDTO dto){
		System.out.println("/idCheck 진입!!");

		int idCheck = loginService.idCheck(dto);
		System.out.println("/idCheck // idCheck : " + idCheck);

		return idCheck;
	}

	@RequestMapping(value = "/loginCheck")
	public int loginCheck(MemberDTO dto){
		System.out.println("/loginCheck 진입!!");

		System.out.println("member_id : "+dto.getMember_id());
		System.out.println("m_pass : "+dto.getM_pass());
		System.out.println("m_enc : "+dto.getM_enc());
		MemberDTO d;
		
//		암호화 체크 on 했을시
		if(dto.getM_enc().equals("on")) {
			System.out.println("m_enc == on 진입");
			
			d = loginService.selectMember(dto);
			String m_pass = d.getM_pass();
			String m_sha256 = new Sha256().getSHA256(m_pass);
			d.setM_sha256(m_sha256);
			d.setM_enc(dto.getM_enc());
			loginService.updateSha(d);
			
		}else if(dto.getM_enc().equals("off")) {
			System.out.println("m_enc == off 진입");
			
			d = loginService.selectMember(dto);
			d.setM_sha256("off");
			d.setM_enc(dto.getM_enc());
			loginService.updateSha(d);
			
		}
		
		int loginCheck = loginService.loginCheck(dto);
		
		System.out.println("/loginCheck // loginCheck : " + loginCheck);

		return loginCheck;
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public int emailSendActionPost(MemberDTO dto) {
		System.out.println("/sendEmail.post() 접근");

		MemberDTO d = loginService.selectMember(dto);
		System.out.println("d : "+d);
		if(d == null) {
			int incorrect = 0;
			return incorrect;
		}
		System.out.println("d.email : "+d.getM_email());
		System.out.println("dto.email : "+dto.getM_email());

		//		사용자가 searchId.jsp 에서 입력한 전화번호와 member table의 등록된 이름이 속한 행에 있는 email이 일치하는지 확인
		if(d.getM_email().equals(dto.getM_email())) {

			String m_email = dto.getM_email();
			
//			6자리 랜덤 숫자
			java.util.Random generator = new java.util.Random();
	        generator.setSeed(System.currentTimeMillis());
			int confirmNumber = generator.nextInt(1000000) % 1000000;

//			이메일 전송 설정
			String host = "localhost:8080";
			String from = "momumu8282@gmail.com";
			String to = m_email;
			String subject = "EXCITING AMUSEMENT 아이디 찾기 인증메일";
			String sha256 = new Sha256().getSHA256(to);
			String content = "EXCITING AMUSEMENT 웹페이지에서 다음 인증코드 6자리를 입력하세요 >> "+confirmNumber;
			//		"<a href='" + host + "/sendEmail?m_sha256=" + sha256 + "'>이메일 인증하기</a>";

			loginService.updateSha(dto);

			Properties prop = new Properties(); 

			prop.put("mail.smtp.user", from);
			prop.put("mail.smtp.host", "smtp.googlemail.com");
			prop.put("mail.smtp.port", "465");
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.debug", "true");
			prop.put("mail.smtp.socketFactory.port", "465");
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			prop.put("mail.smtp.socketFactory.fallback", "false");

			try {
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(prop, auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses);
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO,toAddr);
				msg.setContent(content, "text/html;charset=UTF8");
				Transport.send(msg);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("/sendEmail 종료");


			return confirmNumber;
			
		}else {
			int incorrect = -1;
			return incorrect;
		}
	}	

	@RequestMapping(value = "/checkNumber", method = RequestMethod.POST)
	public void checkNumber(MemberDTO dto) {
		System.out.println("/checkNumber.post() 접근");

		int result = loginService.shaCheck(dto);
		if(result == 1) {
			System.out.println("shaCheck/result == 1");
		}else {
			System.out.println("shaCheck/result == 0");
		}

	}	
	
	@RequestMapping(value = "/imageCheck")
	public String imageCheck(MemberDTO dto,HttpServletRequest request){
		System.out.println("/imageCheck 진입!!");
		
		HttpSession session = request.getSession();
		String member_id = session.getAttribute("member_id")+"";
				
		dto.setMember_id(member_id);
		
		MemberDTO d = loginService.selectMember(dto);
		
		String m_image = d.getM_image();
		
		return m_image;
	}

}
