package com.BScamp.MovieTheater.service;

import java.util.List;

import com.BScamp.MovieTheater.entity.Category;

public interface CategoryService {

	public List<Category> getAll();

	public Category get(int id);

	public Category create(Category category);

	public Category update(int id, Category category);

	public boolean delete(int id);

}
