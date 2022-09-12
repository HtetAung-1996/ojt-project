package com.BScamp.MovieTheater.service;

import java.util.List;

import com.BScamp.MovieTheater.entity.User;

public interface UserService {
	
	public User createUser(User user);

	public User getUser(int id);

	public List<User> getAllUsers();

	public User updateUser(int id, User user);

	public boolean deleteUser(int id);

	public User checkLoginUser(String gmail, String password);

}
