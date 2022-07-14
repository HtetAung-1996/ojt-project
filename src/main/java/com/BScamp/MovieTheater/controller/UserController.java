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
import com.BScamp.MovieTheater.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		mv.addObject("user", new User());
		return mv;
	}

	@PostMapping("/save")
	public void saveUserInfo(@ModelAttribute("user") User user, HttpServletResponse response, HttpSession session)
			throws IOException {
		session.removeAttribute("error");
		if (user.getName() == "") {
			session.setAttribute("error", "Error!");
			response.sendRedirect("/user/register");
		} else {
			User saved = userService.createUser(user);
			System.out.println("saved " + saved);
			response.sendRedirect("/");
		}
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@PostMapping("/loginCheck")
	public ModelAndView loginCheck(@RequestParam("gmail") String gmail, @RequestParam("password") String password,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.removeAttribute("login_error");
		User user = userService.checkLoginUser(gmail, password);
		if (user == null) {
			session.setAttribute("login_error", "Invalid Gmail and password");
			mv.setViewName("login");
		} else {
			session.setAttribute("login_user", user);
			mv.setViewName("movies");
		}
		return mv;
	}

	@GetMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.removeAttribute("login_user");
		System.out.println("Log Out");
		response.sendRedirect("/");
	}

}