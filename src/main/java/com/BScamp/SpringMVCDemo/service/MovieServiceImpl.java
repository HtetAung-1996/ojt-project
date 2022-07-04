package com.BScamp.SpringMVCDemo.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.BScamp.SpringMVCDemo.entity.Movie;
import com.BScamp.SpringMVCDemo.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Override
	public Movie saveMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Movie getMovie(int id) {
		System.out.println("in service id is" + id);
		return movieRepository.findById(id).orElse(null);
	}

	@Override
	public List<Movie> getMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie updateMovie(int id, Movie mo) {
		Movie movie = getMovie(id);
		if (movie != null) {
			movie.setAdult(mo.getAdult());
			movie.setHomepage(mo.getHomepage());
			movie.setBudget(mo.getBudget());
			movie.setOverview(mo.getOverview());
			movie.setOriginal_title(mo.getOriginal_title());
			movie.setPoster_path(mo.getPoster_path());
			movieRepository.save(movie);

		}
		return movie;
	}

	@Override
	public boolean deleteMovie(int id) {
		movieRepository.deleteById(id);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(uploadPath.getParent());
		System.out.println(uploadPath.toString());

		InputStream inputStream;
		try {
			inputStream = file.getInputStream();
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("save " + filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("file can not save");
			e.printStackTrace();
		}

	}

	@Override
	public Set<String> getType() {

		return movieRepository.getType();

	}

	@Override
	public List<Movie> getCategories(String type) {
		// TODO Auto-generated method stub
		return movieRepository.findByType(type);
	}

}
