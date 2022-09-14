package com.BScamp.MovieTheater.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;

import com.BScamp.MovieTheater.entity.Movie;
import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
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

	@GetMapping("/")
	public ModelAndView homePage(HttpSession session, HttpServletResponse response) throws IOException {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			response.sendRedirect("/user/login");
			return null;
		}
		if (user.getRole() != UserRole.admin) {
			response.sendRedirect("/");
			return null;
		}
		return new ModelAndView("admin", "movies", movieService.getMovies());
	}

	// ------------------- Movie

	@PostMapping("/movie/create")
	public Movie createMovie(@Valid @RequestBody Movie movie) {
		return movieService.saveMovie(movie);
	}

	@PostMapping("/movie/create/poster")
	public String createMovieSavePoster(@RequestParam("poster") MultipartFile poster,
			@RequestParam("fileType") String fileType) {
		String fileName = storageService.saveFile(poster, fileType);
		return fileName;
	}

	@PutMapping("/movie/update/poster")
	public String updateMovieSavePoster(@RequestParam("poster") MultipartFile poster,
			@RequestParam("fileType") String fileType, @RequestParam("filePath") String filePath) {
		String fileName = storageService.updateFile(poster, fileType, filePath);
		return fileName;
	}

	@DeleteMapping(value = "/movie/delete/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) {
		String posterPath = movieService.getMovie(id).getPosterPath();
		boolean isDeleted = movieService.deleteMovie(id);
		if (!isDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		storageService.deleteFile(posterPath);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/movie/update/{id}")
	public Movie updateMovie(@PathVariable int id, @Valid @RequestBody Movie movie) {
		return movieService.updateMovie(id, movie);
	}
	
//	@GetMapping("/user")
//	public List<User> listUser() {
//		return userService.getAllUsers();
//	}
	
//	@GetMapping("/record")
//	public List<Record> listRecord() {
//		return recordService.getRecords();
//	}
//
//	@GetMapping("/user")
//	public List<User> listUser() {
//		return userService.getAllUsers();
//	}
//
//	@GetMapping("/user/{id}")
//	public User getUser(@PathVariable int id) {
//		return userService.getUser(id);
//	}

}
