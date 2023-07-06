package com.exciting.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import com.exciting.login.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

// 작성한 필터(JwtAuthenticationFilter)를 스프링 시큐리티가 사용하라고 알려주는 작업
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig{
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
//	HttpSecurity : 시큐리티 섲렁을 위한 오브젝트(web.xml 대신 HttpSecurity를 이용해 시큐리티 관련 설정을 하는것)
	@Bean
	public void filterChain(HttpSecurity http) throws Exception{
//		http 시큐리티 빌더
		 http.cors() //WebMvConfig에서 이미 설정했으므로 기본 cors 설정.
			.and()
			.csrf() //csrf는 현재 사용하지 않으므로 disable
			.disable()
			.httpBasic() //token을 사용하므로 basic 인증 disable
			.disable()
			.sessionManagement() // session 기반이 아님을 선언
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests() // /와 /auth/** 경로는 인증 안 해도 됨.
			.antMatchers("/","/auth/**").permitAll()
			.anyRequest() // /와 auth/**이외의 모든 경로는 인증해야됨.
			.authenticated();
		
		// filter 등록
		// 매 요청하다  CorsFilter 실행한 후에 
		// jwtAuthenticationFilter 실행한다.
		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
	}

}
