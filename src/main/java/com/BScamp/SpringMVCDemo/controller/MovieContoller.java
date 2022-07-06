package com.BScamp.SpringMVCDemo.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.SpringMVCDemo.entity.Movie;
import com.BScamp.SpringMVCDemo.service.MovieService;

@RestController
//@RequestMapping("demo")
public class MovieContoller {

	@Autowired
	MovieService movieService;

	@GetMapping("/movies")
	public ModelAndView getMovies() {
		ModelAndView mv = new ModelAndView();
		List<Movie> movies = movieService.getMovies();
		mv.addObject("movies", movies);
		mv.addObject("types", movieService.getType());
		mv.setViewName("movies");
		return mv;
	}

	@GetMapping("/search_category")
	public ModelAndView getMoviesbyCategories(@RequestParam("type") String type, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		List<Movie> movies;
		if (type.equals("All")) {
			movies = movieService.getMovies();
		}
		movies = movieService.getCategories(type);
		mv.addObject("movies", movies);
		mv.addObject("types", movieService.getType());
		mv.setViewName("movies");
		return mv;
	}

	@GetMapping("movie/{movie_id}")
	public ModelAndView getMovie(@PathVariable("movie_id") String movie_id) {
		ModelAndView mv = new ModelAndView();
		Movie movie = movieService.getMovie(Integer.parseInt(movie_id));
		mv.addObject("movie", movie);
		mv.setViewName("moviedetails");
		return mv;
	}

	@GetMapping("/movie/save")
	public ModelAndView saveMovie1() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("new_movie", new Movie());
		mv.setViewName("savemovie");
		return mv;
	}

	@PostMapping("/movie/save_data")
	public ModelAndView saveMovie(@ModelAttribute("new_movie") Movie new_movie, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.setAttribute("new_movie", new_movie);
		mv.setViewName("movie_poster");
		return mv;
	}

	@PostMapping("movie/save_poster")
	public ModelAndView savePoster(@RequestParam("poster_path") MultipartFile file, HttpSession session) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		ModelAndView mv = new ModelAndView();
		Movie movie = (Movie) session.getAttribute("new_movie");
		movie.setPoster_path(fileName);
		movieService.saveImg(file, session);
		session.setAttribute("new_movie", movie);
		mv.addObject("movie", movie);
		mv.setViewName("movie_video");
		return mv;
	}

	@PostMapping("movie/save_trailer")
	public ModelAndView saveTrailer(@RequestParam("trailer") MultipartFile file, HttpSession session) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		ModelAndView mv = new ModelAndView();
		Movie movie = (Movie) session.getAttribute("new_movie");
		movie.setTrailer(fileName);
		session.setAttribute("movie", movie);
		movieService.saveImg(file, session);
		mv.setViewName("moviedetails");
		return mv;
	}

	@GetMapping("/movie/save_moviedetails")
	public void saveMovieDetails(HttpSession session, HttpServletResponse response) throws IOException {
		Movie save_movie = movieService.saveMovie((Movie) session.getAttribute("movie"));
		session.invalidate();
		// return "redirect: /movie/"+save_movie.getMovie_id();
		// response.sendRedirect("/demo/movie/"+save_movie.getMovie_id());
		//response.sendRedirect("/demo/movies");
		response.sendRedirect("/movies");
	}

	@PutMapping("/movie/update/{id}")
	public ModelAndView updateMovie(@PathVariable("id") int id, @ModelAttribute("new_movie") Movie new_movie) {
		ModelAndView mv = new ModelAndView();
		Movie movie = movieService.updateMovie(id, new_movie);
		mv.addObject("movie", movie);
		mv.setViewName("movie");
		return mv;
	}

	@DeleteMapping("/movies/delete/{id}")
	public ModelAndView deleteMovie(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView();
		if (movieService.deleteMovie(id)) {
			mv.addObject("delete_msg", "deleted successfully");
		} else {
			mv.addObject("delete_msg", "can not delete!!!!");
		}
		mv.setViewName("movie");
		return mv;
	}

}
