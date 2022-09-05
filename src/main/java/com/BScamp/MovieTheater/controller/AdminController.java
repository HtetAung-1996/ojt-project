package com.BScamp.MovieTheater.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.MovieTheater.entity.Movie;
import com.BScamp.MovieTheater.entity.Record;
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
			response.sendRedirect("/admin/login");
			return null;
		}

		User user = (User) session.getAttribute("login_user");
		if (user.getRole() != UserRole.admin) {
			response.sendRedirect("/admin/login");
			return null;
		}

		List<Movie> movies = movieService.getMovies();

		ModelAndView mv = new ModelAndView();
		mv.addObject("movies", movies);
		mv.addObject("toUpdateMovie", new Movie());
		mv.addObject("activeHome", "active");
		mv.addObject("activeLogin", "");
		mv.addObject("activeRegister", "");
		mv.setViewName("admin");
		return mv;

	}

	// ------------------- Movie

	@GetMapping("/movie/create")
	public ModelAndView createMovie() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("toCreateMovie", new Movie());
		mv.setViewName("admin_create_movie");
		return mv;
	}

	@PostMapping("/movie/create/save_movie")
	public ModelAndView createMovieSaveMovie(@ModelAttribute("movie") Movie movie, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.setAttribute("toCreateMovie", movie);
		mv.setViewName("admin_create_movie_poster");
		return mv;
	}

	@PostMapping("/movie/create/save_poster")
	public ModelAndView createMovieSavePoster(@RequestParam("poster") MultipartFile poster, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Movie toCreateMovie = (Movie) session.getAttribute("toCreateMovie");
		toCreateMovie.setPosterPath(StringUtils.cleanPath(poster.getOriginalFilename()));
		movieService.saveFile(poster, session);
		session.setAttribute("toCreateMovie", toCreateMovie);
		mv.setViewName("admin_create_movie_trailer");
		return mv;
	}

	@PostMapping("/movie/create/save_trailer")
	public ModelAndView saveTrailer(@RequestParam("trailer") MultipartFile trailer, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Movie toCreateMovie = (Movie) session.getAttribute("toCreateMovie");
		toCreateMovie.setTrailer(StringUtils.cleanPath(trailer.getOriginalFilename()));
		session.setAttribute("toCreateMovie", toCreateMovie);
		movieService.saveFile(trailer, session);
		mv.setViewName("admin_movie_detail");
		return mv;
	}

	@GetMapping("/movie/create/save_movie_detail")
	public void saveMovieDetails(HttpSession session, HttpServletResponse response) throws IOException {
		movieService.saveMovie((Movie) session.getAttribute("toCreateMovie"));
		session.invalidate();
		response.sendRedirect("/admin/");
	}

	@GetMapping("/movie/update/update_movie/{id}")
	public ModelAndView updateMoviePage(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("toUpdateMovie", movieService.getMovie(id));
		mv.setViewName("admin_update_movie");
		return mv;
	}

	@PostMapping("/movie/update/{id}")
	public void updateMovie(@PathVariable("id") int id, @ModelAttribute("toUpdateMovie") Movie toUpdateMovie,
			HttpServletResponse response) throws IOException {
		Movie movie = movieService.getMovie(id);
		movie.setTitle(toUpdateMovie.getTitle());
		movie.setOverview(toUpdateMovie.getOverview());
		movie.setBudget(toUpdateMovie.getBudget());
		movie.setType(toUpdateMovie.getType());
		movie.setAdult(toUpdateMovie.getAdult());
		movieService.updateMovie(id, movie);
		response.sendRedirect("/admin/");
	}

	@PostMapping("/movie/delete/{id}")
	public void deleteMovie(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
		if (movieService.deleteMovie(id)) {
			response.sendRedirect("/admin/");
		} else {
		}
	}

	// ------------------- API Only

	@GetMapping("/movie")
	public List<Movie> listMovie() {
		return movieService.getMovies();
	}

	@PostMapping(value = "/movie")
	public Movie createMovie(@ModelAttribute Movie movie) {
		return movieService.saveMovie(movie);
	}

	@GetMapping("/movie/{id}")
	public Movie getMovie(@PathVariable int id) {
		return movieService.getMovie(id);
	}

	@PutMapping(value = "/movie/{id}")
	public Movie updateMovie2(@PathVariable int id, @ModelAttribute Movie movie) {
		return movieService.updateMovie(movie.getId(), movie);
	}

	@DeleteMapping(value = "/movie/{id}")
	public boolean deleteMovie2(@PathVariable int id) {
		return movieService.deleteMovie(id);
	}

	@GetMapping("/record")
	public List<Record> listRecord() {
		return recordService.getRecords();
	}

	@GetMapping("/user")
	public List<User> listUser() {
		return userService.getAllUsers();
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

}
