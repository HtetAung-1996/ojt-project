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
	public ResponseEntity<?> createMovie(@Valid @RequestBody Movie movie) {
		if (!storageService.check(movie.getPosterPath())) {
			return ResponseEntity.badRequest().body("Poster is invalid");
		}
		if (!storageService.check(movie.getTrailerPath())) {
			return ResponseEntity.badRequest().body("Trailer is invalid");
		}
		return ResponseEntity.ok(movieService.create(movie));
	}

	@PostMapping("/file/create")
	public ResponseEntity<String> createFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType
	) {
		String filePath = storageService.create(file, fileType);
		if (filePath == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(filePath);
	}

	@PutMapping("/file/update")
	public ResponseEntity<String> updateFile(
			@RequestParam("file") MultipartFile file,
			@RequestParam("fileType") String fileType,
			@RequestParam("filePath") String filePath
	) {
		String newFilePath = storageService.update(file, fileType, filePath);
		if (newFilePath == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(newFilePath);
	}

	@PutMapping("/movie/update/{id}")
	public ResponseEntity<Movie> updateMovie(
			@PathVariable int id, @Valid @RequestBody Movie movie
	) {
		Movie updatedMovie = movieService.update(id, movie);
		if (updatedMovie == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedMovie);
	}

	@DeleteMapping(value = "/movie/delete/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) {
		Movie movie = movieService.get(id);
		if (movie == null) {
			return ResponseEntity.notFound().build();
		}

		// Delete Movie
		boolean isDeleted = movieService.delete(id);
		if (!isDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		// Delete Poster
		storageService.delete(movie.getPosterPath());

		// Delete Trailer
		storageService.delete(movie.getTrailerPath());

		return ResponseEntity.ok().build();
	}

	@GetMapping("/movie/title/{title}")
	public ResponseEntity<Boolean> findMovieByTitle(
			@PathVariable("title") String title
	) {
		Movie movie = movieService.getByTitle(title);
		if (movie == null) {
			return ResponseEntity.ok().body(false);
		}
		return ResponseEntity.ok().body(true);
	}

	// ------------------- User

	@GetMapping("/user")
	public List<User> listUser() {
		return userService.getAll();
	}

	@PutMapping("/user/update_status")
	public ResponseEntity<?> updateUserStatus(
			@RequestParam int id, @RequestParam String status
	) {
		User user = userService.updateStatus(id, status);
		if (user == null) {
			return ResponseEntity.badRequest()
					.body("User is invalid, Status is invalid");
		}
		return ResponseEntity.ok(user);
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
