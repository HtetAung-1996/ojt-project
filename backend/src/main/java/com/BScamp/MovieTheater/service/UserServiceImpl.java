package com.BScamp.MovieTheater.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public User createUser(User user) {
		user.setCreatedAt(LocalDateTime.now());
		return userRepo.save(user);
	}

	@Override
	public User getUser(int id) {
		return userRepo.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User updateUser(int id, User user) {
		User toUpdateUser = getUser(id);
		if (toUpdateUser != null) {
			toUpdateUser.setName(user.getName());
			toUpdateUser.setPassword(user.getPassword());
			toUpdateUser.setGmail(user.getGmail());
			toUpdateUser.setRole(user.getRole());
			toUpdateUser.setStartJoinDate(user.getStartJoinDate());
			toUpdateUser.setLastJoinDate(user.getLastJoinDate());
			toUpdateUser.setAccessCount(user.getAccessCount());
			toUpdateUser.setUpdatedAt(LocalDateTime.now());
			userRepo.save(toUpdateUser);
		}
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
		User user = getUser(id);
		if (user != null) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public User checkLoginUser(String gmail, String password) {
		return userRepo.findByGmailAndPassword(gmail, password);
	}
}
