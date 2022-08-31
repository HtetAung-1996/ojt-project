package com.BScamp.MovieTheater.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.MovieTheater.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("activeHome", "");
		mv.addObject("activeLogin", "active");
		mv.addObject("activeRegister", "");
		mv.setViewName("login");
		return mv;
	}
	
	@GetMapping("/admin/login")
	public ModelAndView adminLogin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin_login");
		return mv;
	}


	@GetMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.removeAttribute("login_user");
		response.sendRedirect("/");
	}

}
