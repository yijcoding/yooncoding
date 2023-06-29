package com.exciting.login.controller;

import java.security.MessageDigest;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import utils.Gmail;

@Controller
@Data
@Log4j2
public class LoginEmailController {
	

	@RequestMapping(value = "/sha256", method = RequestMethod.GET)
	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-2565");
			byte[] salt = "Hello This is salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			for(int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length() == 1) result.append("0");
				result.append(hex);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public static void emailSendAction() {
		
		String host = "localhost:8080";
		String from = "momumu8282@gmail.com";
		String to = "받는사람 이메일 주소";
		String subject = "강의평가를 위한 이메일 인증 메일입니다.";
		String content = "다음 링크에 접속하여 이메일 인증을 진행하세요." +
		"<a href='" + host + "/send?code=" + getSHA256(to) + "'>이메일 인증하기</a>";
		
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
		
		
}
	
}
