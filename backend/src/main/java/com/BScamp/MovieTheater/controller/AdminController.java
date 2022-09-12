package com.BScamp.MovieTheater.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.MovieTheater.entity.Movie;
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

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect("/user/login");
			return null;
		}
		if (user.getRole() != UserRole.admin) {
			response.sendRedirect("/");
			return null;
		}
		return new ModelAndView("admin", "movies", movieService.getMovies());
	}

	// ------------------- Movie

	@GetMapping("/movie/create")
	public ModelAndView createMovie() {
		return new ModelAndView("admin_create_movie", "toCreateMovie", new Movie());
	}

	@PostMapping("/movie/create/save_movie")
	public ModelAndView createMovieSaveMovie(@ModelAttribute("movie") Movie movie, HttpSession session) {
		session.setAttribute("toCreateMovie", movie);
		return new ModelAndView("admin_create_movie_poster");
	}

	@PostMapping("/movie/create/save_poster")
	public ModelAndView createMovieSavePoster(@RequestParam("poster") MultipartFile poster, HttpSession session) {
		movieService.saveFile(poster, session);
		Movie toCreateMovie = (Movie) session.getAttribute("toCreateMovie");
		toCreateMovie.setPosterPath(StringUtils.cleanPath(poster.getOriginalFilename()));
		session.setAttribute("toCreateMovie", toCreateMovie);
		return new ModelAndView("admin_create_movie_trailer");
	}

	@PostMapping("/movie/create/save_trailer")
	public ModelAndView saveTrailer(@RequestParam("trailer") MultipartFile trailer, HttpSession session) {
		movieService.saveFile(trailer, session);
		Movie toCreateMovie = (Movie) session.getAttribute("toCreateMovie");
		toCreateMovie.setTrailer(StringUtils.cleanPath(trailer.getOriginalFilename()));
		session.setAttribute("toCreateMovie", toCreateMovie);
		return new ModelAndView("admin_movie_detail");
	}

	@GetMapping("/movie/create/save_movie_detail")
	public void saveMovieDetails(HttpSession session, HttpServletResponse response) throws IOException {
		movieService.saveMovie((Movie) session.getAttribute("toCreateMovie"));
		session.removeAttribute("toCreateMovie");
		response.sendRedirect("/admin/");
	}

	@GetMapping("/movie/update/update_movie/{id}")
	public ModelAndView updateMoviePage(@PathVariable("id") int id) {
		return new ModelAndView("admin_update_movie", "toUpdateMovie", movieService.getMovie(id));
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
		}
	}

//	// ------------------- API Only
//
//	@GetMapping("/movie")
//	public List<Movie> listMovie() {
//		return movieService.getMovies();
//	}
//
//	@PostMapping(value = "/movie")
//	public Movie createMovie(@ModelAttribute Movie movie) {
//		return movieService.saveMovie(movie);
//	}
//
//	@GetMapping("/movie/{id}")
//	public Movie getMovie(@PathVariable int id) {
//		return movieService.getMovie(id);
//	}
//
//	@PutMapping(value = "/movie/{id}")
//	public Movie updateMovie2(@PathVariable int id, @ModelAttribute Movie movie) {
//		return movieService.updateMovie(movie.getId(), movie);
//	}
//
//	@DeleteMapping(value = "/movie/{id}")
//	public boolean deleteMovie2(@PathVariable int id) {
//		return movieService.deleteMovie(id);
//	}
//
//	@GetMapping("/record")
//	public List<Record> listRecord() {
//		return recordService.getRecords();
//	}
//
//	@GetMapping("/user")
//	public List<User> listUser() {
//		return userService.getAllUsers();
//	}
//
//	@GetMapping("/user/{id}")
//	public User getUser(@PathVariable int id) {
//		return userService.getUser(id);
//	}

}
