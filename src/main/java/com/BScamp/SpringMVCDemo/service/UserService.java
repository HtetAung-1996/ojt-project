package com.BScamp.SpringMVCDemo.service;

import java.util.List;

import com.BScamp.SpringMVCDemo.entity.MovieUser;

public interface UserService {
	
	public MovieUser saveMovieUser(MovieUser movieUser);

	public MovieUser getMovieUser(int id);

	public List<MovieUser> getMovieUsers();

	public MovieUser updateMovieUser(int id, MovieUser movieUser);

	public boolean deleteMovieUser(int id);

	public MovieUser checkLoginUser(String gmail, String password);

}
