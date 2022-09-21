package com.BScamp.MovieTheater;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.BScamp.MovieTheater.entity.Category;
import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
import com.BScamp.MovieTheater.entity.UserStatus;
import com.BScamp.MovieTheater.service.CategoryService;
import com.BScamp.MovieTheater.service.StorageService;
import com.BScamp.MovieTheater.service.UserService;

@SpringBootApplication
public class MovieTheaterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieTheaterApplication.class, args);
	}

	@Autowired
	UserService userService;

	@Autowired
	StorageService storageService;

	@Autowired
	CategoryService categoryService;

	@Override
	public void run(String... args) throws Exception {

		User adminUser = userService.get(1);
		if (adminUser == null) {
			userService.create(
					new User(
							1, "Admin", "1111", "admin@gmail.com",
							UserStatus.active, UserRole.admin, LocalDate.now(),
							null, 0, LocalDateTime.now(), null
					)
			);
		}

		if (categoryService.getAll().size() <= 0) {
			categoryService.create(new Category(1, "Adventure"));
			categoryService.create(new Category(2, "Science Fiction"));
			categoryService.create(new Category(3, "Horror"));
			categoryService.create(new Category(4, "Romance"));
			categoryService.create(new Category(5, "Thriller"));
			categoryService.create(new Category(6, "Comedy"));
			categoryService.create(new Category(7, "Drama"));
			categoryService.create(new Category(8, "Fantasy"));
			categoryService.create(new Category(9, "Mystery"));
			categoryService.create(new Category(10, "Action"));
		}

		storageService.clearAll();

	}
}
