package com.BScamp.MovieTheater.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
import com.BScamp.MovieTheater.service.MovieService;
import com.BScamp.MovieTheater.service.RecordService;
import com.BScamp.MovieTheater.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	MovieService movieService;
	@Autowired
	RecordService recordService;
	@Autowired
	UserService userService;

	@GetMapping("/")
	public ModelAndView homePage(HttpSession session, HttpServletResponse response) throws IOException {

		if (session.getAttribute("login_user") == null) {
			response.sendRedirect("/user/login");
			return null;
		}

		User user = (User) session.getAttribute("login_user");
		if (user.getRole() != UserRole.admin) {
			response.sendRedirect("/");
			return null;
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("activeHome", "active");
		mv.addObject("activeLogin", "");
		mv.addObject("activeRegister", "");
		mv.setViewName("admin");
		return mv;

	}

	// @GetMapping("/movie/create")
	// public ModelAndView createMovie() {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("movie", new Movie());
	// mv.setViewName("create_movie");
	// return mv;
	// }
	//
	// @PostMapping("/movie/save")
	// public ModelAndView saveMovie(@ModelAttribute("movie") Movie movie,
	// HttpSession session) {
	// ModelAndView mv = new ModelAndView();
	// session.setAttribute("new_movie", movie);
	// mv.setViewName("create_movie_poster");
	// return mv;
	// }
	//
	// @PostMapping("movie/save_poster")
	// public ModelAndView savePoster(@RequestParam("poster_path") MultipartFile
	// file, HttpSession session) {
	// ModelAndView mv = new ModelAndView();
	// String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	// Movie movie = (Movie) session.getAttribute("new_movie");
	// movie.setPosterPath(fileName);
	// movieService.saveImg(file, session);
	// session.setAttribute("new_movie", movie);
	// mv.addObject("movie", movie);
	// mv.setViewName("create_movie_video");
	// return mv;
	// }
	//
	// @PostMapping("movie/save_trailer")
	// public ModelAndView saveTrailer(@RequestParam("trailer") MultipartFile file,
	// HttpSession session) {
	// ModelAndView mv = new ModelAndView();
	// String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	// Movie movie = (Movie) session.getAttribute("new_movie");
	// movie.setTrailer(fileName);
	// session.setAttribute("movie", movie);
	// movieService.saveImg(file, session);
	// mv.setViewName("movie_details");
	// return mv;
	// }
	//
	// @GetMapping("/movie/save_movie_details")
	// public void saveMovieDetails(HttpSession session, HttpServletResponse
	// response) throws IOException {
	// movieService.saveMovie((Movie) session.getAttribute("movie"));
	// session.invalidate();
	// response.sendRedirect("/");
	// }
	//
	// @PutMapping("/movie/update/{id}")
	// public ModelAndView updateMovie(@PathVariable("id") int id,
	// @ModelAttribute("new_movie") Movie new_movie) {
	// ModelAndView mv = new ModelAndView();
	// Movie movie = movieService.updateMovie(id, new_movie);
	// mv.addObject("movie", movie);
	// mv.setViewName("movie");
	// return mv;
	// }
	//
	// @DeleteMapping("/movies/delete/{id}")
	// public ModelAndView deleteMovie(@PathVariable("id") int id) {
	// ModelAndView mv = new ModelAndView();
	// if (movieService.deleteMovie(id)) {
	// mv.addObject("delete_msg", "deleted successfully");
	// } else {
	// mv.addObject("delete_msg", "can not delete!!!!");
	// }
	// mv.setViewName("movie");
	// return mv;
	// }

}
