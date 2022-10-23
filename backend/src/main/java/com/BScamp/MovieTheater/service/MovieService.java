package com.BScamp.MovieTheater.service;

import java.util.List;

import com.BScamp.MovieTheater.entity.Category;
import com.BScamp.MovieTheater.entity.Movie;

public interface MovieService {

	public List<Movie> getAll();

	public Movie get(int id);

	public Movie create(Movie movie);

	public Movie update(int id, Movie movie);

	public boolean delete(int id);

	public List<Movie> getAllByCategory(Category category);

	public Movie getByTitle(String title);

}
