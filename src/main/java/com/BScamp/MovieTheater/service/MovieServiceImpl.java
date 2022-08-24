package com.BScamp.MovieTheater.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.BScamp.MovieTheater.entity.Movie;
import com.BScamp.MovieTheater.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepo;

	@Override
	public Movie saveMovie(Movie movie) {
		movie.setCreatedAt(LocalDate.now());
		return movieRepo.save(movie);
	}

	@Override
	public Movie getMovie(int id) {
		return movieRepo.findById(id).orElse(null);
	}

	@Override
	public List<Movie> getMovies() {
		return movieRepo.findAll();
	}

	@Override
	public Movie updateMovie(int id, Movie mo) {
		Movie movie = getMovie(id);
		if (movie != null) {
			movie.setTitle(mo.getTitle());
			movie.setPosterPath(mo.getPosterPath());
			movie.setBudget(mo.getHomePage());
			movie.setHomePage(mo.getHomePage());
			movie.setTrailer(mo.getTrailer());
			movie.setOverview(mo.getOverview());
			movie.setType(mo.getType());
			movie.setAdult(mo.getAdult());
			movie.setUpdatedAt(LocalDate.now());
			movieRepo.save(movie);
		}
		return movie;
	}

	@Override
	public boolean deleteMovie(int id) {
		movieRepo.deleteById(id);
		return true;
	}

	@Override
	public void saveImg(MultipartFile file, HttpSession session) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		ServletContext context = session.getServletContext();
		Path uploadPath = Paths.get(context.getRealPath("/") + "images");
		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(uploadPath.getParent());
		System.out.println(uploadPath.toString());

		try {
			InputStream inputStream = file.getInputStream();
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("save " + filePath);
		} catch (IOException e) {
			System.out.println("file can not save");
			e.printStackTrace();
		}
		
	}

	@Override
	public Set<String> getType() {
		return movieRepo.getType();
	}

	@Override
	public List<Movie> getCategories(String type) {
		return movieRepo.findByType(type);
	}

}
