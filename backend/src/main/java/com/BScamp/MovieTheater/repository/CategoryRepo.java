package com.BScamp.MovieTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.BScamp.MovieTheater.entity.Category;

@EnableJpaRepositories
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
