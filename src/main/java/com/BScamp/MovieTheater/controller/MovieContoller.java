package com.BScamp.MovieTheater.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.MovieTheater.entity.Movie;
import com.BScamp.MovieTheater.service.MovieService;

@RestController
public class MovieContoller {

	@Autowired
	MovieService movieService;

	@GetMapping("/")
	public ModelAndView getMovies() {
		ModelAndView mv = new ModelAndView();
		List<Movie> movies = movieService.getMovies();
		mv.addObject("movies", movies);
		Set<String> catList = new HashSet<String>();
		catList.add("All");
		catList.addAll(movieService.getType());
		mv.addObject("types", catList);
		mv.addObject("activeHome", "active");
		mv.addObject("activeLogin", "");
		mv.addObject("activeRegister", "");
		mv.setViewName("movies");
		return mv;
	}

//	@GetMapping("/search_category")
//	public ModelAndView getMoviesbyCategories(@RequestParam("type") String type, HttpServletResponse response) {
//		ModelAndView mv = new ModelAndView();
//		List<Movie> movies;
//		if (type.equals("All")) {
//			movies = movieService.getMovies();
//		} else {
//			movies = movieService.getCategories(type);
//		}
//		mv.addObject("movies", movies);
//		mv.addObject("types", movieService.getType());
//		mv.setViewName("movies");
//		return mv;
//	}

	@GetMapping("/category/{category}")
	public ModelAndView getMoviesbyCategory(@PathVariable("category") String category, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		List<Movie> movies;
		if (category.equals("All")) {
			movies = movieService.getMovies();
		} else {
			movies = movieService.getCategories(category);
		}
		mv.addObject("movies", movies);
		Set<String> catList = new HashSet<String>();
		catList.add("All");
		catList.addAll(movieService.getType());
		mv.addObject("types", catList);
		mv.addObject("activeHome", "active");
		mv.addObject("activeLogin", "");
		mv.addObject("activeRegister", "");
		mv.setViewName("movies");
		return mv;
	}

	@GetMapping("/movie/details/{movie_id}")
	public ModelAndView getMovie(@PathVariable("movie_id") String movie_id) {
		ModelAndView mv = new ModelAndView();
		Movie movie = movieService.getMovie(Integer.parseInt(movie_id));
		mv.addObject("movie", movie);
		mv.setViewName("movie_details");
		return mv;
	}

}
