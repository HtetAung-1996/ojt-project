package com.BScamp.MovieTheater.service;

import java.util.List;
import java.util.Set;

import com.BScamp.MovieTheater.entity.Movie;

public interface MovieService {
	
	public Movie saveMovie(Movie movie);

	public Movie getMovie(int id);

	public List<Movie> getMovies();

	public Movie updateMovie(int id, Movie mo);

	public boolean deleteMovie(int id);

	public Set<String> getType();

	public List<Movie> getCategories(String type);
	
}
