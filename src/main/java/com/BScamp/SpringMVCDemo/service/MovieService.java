package com.BScamp.SpringMVCDemo.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.BScamp.SpringMVCDemo.entity.Movie;

public interface MovieService {
	
	public Movie saveMovie(Movie movie);

	public Movie getMovie(int id);

	public List<Movie> getMovies();

	public Movie updateMovie(int id, Movie mo);

	public boolean deleteMovie(int id);

	public void saveImg(MultipartFile file, HttpSession session);

	public Set<String> getType();

	public List<Movie> getCategories(String type);
	
}
