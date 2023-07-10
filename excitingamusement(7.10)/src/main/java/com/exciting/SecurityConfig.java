//package com.exciting;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//	
//	//csrf().~~~ => 설정한 경로로 시작하는 url은 csrf 검증을 하지 않겠다는 설정
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		 http.authorizeHttpRequests().requestMatchers(
//	                new AntPathRequestMatcher("/**")).permitAll()
//	            .and()
//	                .csrf().ignoringRequestMatchers(
//	                        new AntPathRequestMatcher("/amusement/**"))
//				.and()
//			        .headers()
//			        .addHeaderWriter(new XFrameOptionsHeaderWriter(
//			        		XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
//		 		.and()	//로그인 URL 설정
//		 			.formLogin()
//		 			.loginPage("/user/login")	//로그인 페이지
//		 			.defaultSuccessUrl("/")		//로그인 성공 시 Root 경로로 이동하도록 설정
//		 		.and()	//로그아웃 URL 설정
//		 			.logout()
//		 			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//		 			.logoutSuccessUrl("/")
//		 			.invalidateHttpSession(true); //로그아웃 시 기존에 생성된 session 삭제
//		 
//	        return http.build();
//	}
//	
//	//PasswordEncoder 빈(bean) 생성, 설정
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	AuthenticationManager authenticationManager(
//			AuthenticationConfiguration authenticationConfiguration) 
//			throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//	
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
