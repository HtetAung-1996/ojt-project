package com.BScamp.MovieTheater.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.BScamp.MovieTheater.entity.Category;
import com.BScamp.MovieTheater.entity.Movie;
import com.BScamp.MovieTheater.service.CategoryService;
import com.BScamp.MovieTheater.service.MovieService;
import com.BScamp.MovieTheater.service.StorageService;

@RestController
public class MovieContoller {

	@Autowired
	MovieService movieService;

	@Autowired
	StorageService storageService;

	@Autowired
	CategoryService categoryServie;

	@GetMapping("/movie")
	public List<Movie> getMovies() {
		return movieService.getAll();
	}

	@GetMapping("/movie/category/{category_id}")
	public ResponseEntity<List<Movie>> getMoviesByCategory(
			@PathVariable("category_id") int categoryID
	) {
		Category category = categoryServie.get(categoryID);
		List<Movie> movieList = movieService.getAllByCategory(category);
		return ResponseEntity.ok().body(movieList);
	}

	@GetMapping("/movie/{movie_id}")
	public ResponseEntity<Movie> getMovie(
			@PathVariable("movie_id") int movieID
	) {
		Movie movie = movieService.get(movieID);
		if (movie == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(movie);
	}

	@GetMapping("/media/{fileType}/{fileName}")
	public ResponseEntity<?> getPoster(
			@PathVariable("fileType") String fileType,
			@PathVariable("fileName") String fileName
	) throws IOException {
		MediaType contentType = MediaType.IMAGE_PNG;
		switch (fileType) {
			case "mp4" :
				contentType = MediaType.APPLICATION_OCTET_STREAM;
				break;
			case "jpg" :
				contentType = MediaType.IMAGE_JPEG;
				break;
			case "png" :
				contentType = MediaType.IMAGE_PNG;
				break;
			default :
				return ResponseEntity.badRequest()
						.body("Unsupported File Type");
		}
		byte[] fileBytes = storageService.load(fileName);
		if (fileBytes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().contentType(contentType).body(fileBytes);
	}

}
