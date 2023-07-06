package com.exciting.login.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenProvider tokenProvider;
	
//	request와,response를 사용하는 이유는 요청 응답할때 HTTP 헤더를 이용하기 위해서다.
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException,IOException{
		
		try {
		// 요청에서 토큰 가져오기.
		String token = parseBearerToken(request);
		log.info("Filter is running...");
//		토큰 검사하기, JWT이므로 인가 서버에 요청하지 않고도 검증 가능.
		if(token != null && !token.equalsIgnoreCase("null")) {
//		userId 가져오기(token 인증), 위조된 경우 예외 처리된다.(요청 header의  subject의 값을 반환.(사용자의 실제 아이디가 아니라, 사용자를 구분하는 식별자인 (id)를 가져오는것)
			String userId = tokenProvider.validateAndGetUserId(token);
			log.info("Authenticated user ID : "+ userId);
//			인증완료; SecurityContextHolder에 등록해야 인증된 사용자라고 생각한다.
//			UsernamePasswordAuthenticationToken을 작성(첫번째 매개변수 userId가, @AuthenticationPrincipal 애너테이션의 값이 된다.)
			AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null,AuthorityUtils.NO_AUTHORITIES);
//			사용자의 인증정보 저장
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//			SecurityContext에 인증된 사용자를 등록
			SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
			securityContext.setAuthentication(authentication);
//			마지막으로 홀더에 등록
			SecurityContextHolder.setContext(securityContext);
		}
		}catch(Exception ex) {
			logger.error("Could not set user authentication in security context",ex);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String parseBearerToken(HttpServletRequest request) {
		
//		Http 요청의 헤더를 파싱해 Bearer 토큰을 리턴한다.(Authorization 에 TOKEN 값이 저징되어 있다.)
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7);
		}
		
		return null;
		
		
	}
}
