package com.BScamp.MovieTheater.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BScamp.MovieTheater.entity.Category;
import com.BScamp.MovieTheater.entity.Movie;
import com.BScamp.MovieTheater.entity.Record;
import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.service.CategoryService;
import com.BScamp.MovieTheater.service.MovieService;
import com.BScamp.MovieTheater.service.RecordService;
import com.BScamp.MovieTheater.service.StorageService;
import com.BScamp.MovieTheater.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	MovieService movieService;

	@Autowired
	StorageService storageService;

	@Autowired
	RecordService recordService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	// ------------------- Movie

	@PostMapping("/movie/create")
	public Movie createMovie(@Valid @RequestBody Movie movie) {
		return movieService.create(movie);
	}

	@PostMapping("/movie/create/poster")
	public String createMovieSavePoster(
			@RequestParam("poster") MultipartFile poster,
			@RequestParam("fileType") String fileType
	) {
		String fileName = storageService.save(poster, fileType);
		return fileName;
	}

	@PutMapping("/movie/update/poster")
	public String updateMovieSavePoster(
			@RequestParam("poster") MultipartFile poster,
			@RequestParam("fileType") String fileType,
			@RequestParam("filePath") String filePath
	) {
		String fileName = storageService.update(poster, fileType, filePath);
		return fileName;
	}

	@DeleteMapping(value = "/movie/delete/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) {
		String posterPath = movieService.get(id).getPosterPath();
		boolean isDeleted = movieService.delete(id);
		if (!isDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		storageService.delete(posterPath);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/movie/update/{id}")
	public Movie updateMovie(
			@PathVariable int id, @Valid @RequestBody Movie movie
	) {
		return movieService.update(id, movie);
	}

	// ------------------- User

	@GetMapping("/user")
	public List<User> listUser() {
		return userService.getAll();
	}

	@PutMapping("/user/update_status")
	public User updateUserStatus(
			@RequestParam int id, @RequestParam String status
	) {
		return userService.updateStatus(id, status);
	}

	@GetMapping("/user_status")
	public List<String> listUserStatus() {
		return userService.getAllStatus();
	}

	// ------------------- Record

	@GetMapping("/record")
	public List<Record> listRecord() {
		return recordService.getAll();
	}

	// ------------------- Category

	@GetMapping("/category")
	public List<Category> listCategory() {
		return categoryService.getAll();
	}

}
