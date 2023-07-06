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
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
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
			.authorizeRequests() // /와 /login/** 경로는 인증 안 해도 됨.
			.antMatchers("/mypage/getMember","/mypage/upload").hasRole("user")
			.antMatchers("/","/login/**","/email","/mypage/getMemberByKakaoId").permitAll()
			.anyRequest() // antMatchers를 제외한 모든 API
			.authenticated();// token 인증이 있어야 함, 역할 까지는 필요 없음
		
		// filter 등록
		// 매 요청하다  CorsFilter 실행한 후에 
		// jwtAuthenticationFilter 실행한다.
		return http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class).build();
	}

}
