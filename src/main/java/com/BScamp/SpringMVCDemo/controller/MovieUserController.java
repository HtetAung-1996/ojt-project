package com.BScamp.SpringMVCDemo.controller;

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

import com.BScamp.SpringMVCDemo.entity.MovieUser;
import com.BScamp.SpringMVCDemo.service.UserService;

@RestController
@RequestMapping("/theatre")
public class MovieUserController {

	@Autowired
	UserService userService;

	@GetMapping("/save")
	public ModelAndView getUserInfo() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new MovieUser());
		mv.setViewName("user_register");
		System.out.println("start");
		return mv;
	}

	@PostMapping("/save_info")
	public void saveUserInfo(@ModelAttribute("user") MovieUser movieUser, HttpServletResponse response)
			throws IOException {
		MovieUser saved = userService.saveMovieUser(movieUser);
		System.out.println("saved " + saved);
		response.sendRedirect("/movies");
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
		MovieUser login_user = userService.checkLoginUser(gmail, password);
		if (login_user == null) {
			session.setAttribute("login_error", "Invalid Gmail and password");
			mv.setViewName("login");
		} else {
			// session.invalidate();
			session.setAttribute("login_user", login_user);
			mv.setViewName("movies");
		}

		return mv;
	}

}
