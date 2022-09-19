package com.BScamp.MovieTheater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.BScamp.MovieTheater.entity.Category;
import com.BScamp.MovieTheater.entity.Movie;

@EnableJpaRepositories
public interface MovieRepo extends JpaRepository<Movie, Integer> {

	public List<Movie> findByCategory(Category category);

}
