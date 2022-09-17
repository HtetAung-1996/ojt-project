package com.BScamp.MovieTheater;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
import com.BScamp.MovieTheater.service.UserService;

@SpringBootApplication
public class MovieTheaterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieTheaterApplication.class, args);
	}

	@Autowired
	UserService userService;

	@Override
	public void run(String... args) throws Exception {

		userService.createUser(
				new User(
						1, "Admin", "1111", "admin@gmail.com", UserRole.admin,
						LocalDate.now(), null, 0, LocalDateTime.now(), null
				)
		);

	}
}
