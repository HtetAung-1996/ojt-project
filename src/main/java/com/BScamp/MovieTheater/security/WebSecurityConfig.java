//package com.BScamp.MovieTheater.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////	private final UserDetailsService userDetailsService = null;
////
////	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
////	}
////
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		super.configure(http);
////	}
////
////	@Bean
////	PasswordEncoder passwordEncoder() {
////		return new BCryptPasswordEncoder();
////	}
//
//}