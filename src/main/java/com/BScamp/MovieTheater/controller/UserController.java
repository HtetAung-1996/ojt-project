package com.BScamp.MovieTheater.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
import com.BScamp.MovieTheater.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

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

	@GetMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
		response.sendRedirect("/");
	}

	@PostMapping("/loginCheck")
	public void loginCheck(@RequestParam("gmail") String gmail, @RequestParam("password") String password,
			HttpSession session, HttpServletResponse response) throws IOException {
		session.removeAttribute("loginError");
		User user = userService.checkLoginUser(gmail, password);
		if (user == null) {
			session.setAttribute("loginError", "Invalid Gmail and password");
			response.sendRedirect("/user/login");
		} else {
			if (user.getRole() == UserRole.admin) {
				session.setAttribute("loginUser", user);
				response.sendRedirect("/admin/");
			} else {
				session.setAttribute("loginUser", user);
				response.sendRedirect("/");
			}
		}
	}

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		mv.addObject("user", new User());
		mv.addObject("activeHome", "");
		mv.addObject("activeLogin", "");
		mv.addObject("activeRegister", "active");
		return mv;
	}

	@PostMapping("/save")
	public void saveUserInfo(@ModelAttribute("user") User user, HttpServletResponse response, HttpSession session)
			throws IOException {
		session.removeAttribute("loginError");
		if (user.getName() == "") {
			session.setAttribute("loginError", "Error!");
			response.sendRedirect("/user/register");
		} else {
			User createdUser = userService.createUser(user);
			session.setAttribute("loginUser", createdUser);
			response.sendRedirect("/");
		}
	}

}