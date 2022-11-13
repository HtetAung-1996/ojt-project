package com.BScamp.MovieTheater.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authManager(
			AuthenticationConfiguration authConfig
	) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.csrf().disable().authorizeRequests().mvcMatchers("/**").permitAll()
	//
	// //
	// .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	// // .mvcMatchers("/", "/user/register", "/user/login").permitAll()
	// // .mvcMatchers("/logout", "/admin/**").hasRole("ADMIN")
	// // .anyRequest().authenticated().and()
	// // .formLogin().loginPage("/user/login").defaultSuccessUrl("/").and()
	// // .logout().invalidateHttpSession(true).logoutSuccessUrl("/")
	// ;
	// }
	//
	// @Override
	// public void configure(WebSecurity web) throws Exception {
	// web.debug(false).ignoring().antMatchers("/images/**", "/js/**",
	// "/css/**");
	// }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// Enable CORS and disable CSRF
		http.cors().and().csrf().disable().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and();

		// Set session management to stateless
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

		// Set permissions on endpoints
		http.authorizeRequests()
				// Our public endpoints
				// .antMatchers("/api/public/**").permitAll()
				.antMatchers("/user/signin").permitAll()
				// Our private endpoints
				.anyRequest().authenticated().and();

		// Add JWT token filter
		http.authenticationProvider(authProvider());
		http.addFilterBefore(
				authTokenFilter(), UsernamePasswordAuthenticationFilter.class
		);

		// http.cors().and().csrf().disable().exceptionHandling()
		// .authenticationEntryPoint(unauthorizedHandler).and()
		// .sessionManagement()
		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// .authorizeRequests().antMatchers("/user/signin").permitAll()
		// .anyRequest().authenticated();
		//
		// http.authenticationProvider(authProvider());
		// http.addFilterBefore(
		// authTokenFilter(), UsernamePasswordAuthenticationFilter.class
		// );

		return http.build();
	}

}