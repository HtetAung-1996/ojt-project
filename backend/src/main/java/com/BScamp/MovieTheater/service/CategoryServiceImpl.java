package com.BScamp.MovieTheater.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BScamp.MovieTheater.entity.Category;
import com.BScamp.MovieTheater.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categroyRepo;

	@Override
	public List<Category> getAll() {
		return categroyRepo.findAll();
	}

	@Override
	public Category get(int id) {
		return categroyRepo.findById(id).orElse(null);
	}

	@Override
	public Category create(Category category) {
		return categroyRepo.save(category);
	}

	@Override
	public Category update(int id, Category category) {
		category.setId(id);
		return categroyRepo.save(category);
	}

	@Override
	public boolean delete(int id) {
		categroyRepo.deleteById(id);
		return true;
	}

}
