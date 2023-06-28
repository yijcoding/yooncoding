package com.exciting.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class WebSocketHandler extends TextWebSocketHandler{
	
	@Autowired
	SqlSession sqlsession;
	
	List<WebSocketSession> connectSessions = new ArrayList<WebSocketSession>();
	Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//================ "/echo" ================
//		System.out.println("session.getId() = " + session.getId());
//		System.out.println("session.getAttributes() = " + session.getAttributes());
//		System.out.println(session.getHandshakeHeaders());
//		System.out.println("session.getUri() = " + session.getUri());
//		System.out.println("session = " + session);
//		System.out.println(session.getUri().toString());
		
		
		int idx = session.getUri().toString().indexOf("=");
		//System.out.println("idx = " + idx);
		
		String member_id = session.getUri().toString().substring(idx + 1);
		System.out.println("member_id = " + member_id);
		
		log.info(session.getUri().toString().substring(idx + 1) + "님이 채팅방에 입장했습니다");
//		String enterMsg = session.getUri().toString().substring(idx + 1) + "님이 채팅방에 입장했습니다";
//		String enterMsg = "<button type=" + "'button' id=" + "'btn' class=" + 
//						"'btn btn-outline-dark mt-auto card-text' onclick=" + "'btn()'>" + "QnA" + "</button>";
//		TextMessage enterMessage = new TextMessage(enterMsg);
//		session.sendMessage(enterMessage);
		
		session.sendMessage(new TextMessage(session.getUri().toString().substring(idx + 1)));
		connectSessions.add(session);
		
		//================ "/alarm" ================
		users.put(session.getId(), session);
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//================ "/echo" ================
		for(WebSocketSession s : connectSessions) {
			int idx = s.getUri().toString().indexOf("=");
			s.sendMessage(new TextMessage(session.getUri().toString().substring(idx + 1) + ":" + message.getPayload()));
		}
		
		
		//================ "/alarm" ================
		String msg = message.getPayload();
		System.out.println("msg = " + msg);
		
	}	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		connectSessions.remove(session);
		log.info(session.getUri().toString().substring(session.getUri().toString().indexOf("=") + 1) + "님이 퇴장했습니다");
		session.sendMessage(new TextMessage(session.getUri().toString().substring(session.getUri().toString().indexOf("=") + 1) + "님이 퇴장했습니다"));
	}
}
