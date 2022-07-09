package com.BScamp.MovieTheater.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(int id, User user) {
		User toUpdateUser = getUser(id);
		if (toUpdateUser != null) {
			toUpdateUser.setName(user.getName());
			toUpdateUser.setGmail(user.getGmail());
			toUpdateUser.setPassword(user.getPassword());
			toUpdateUser.setStartJoinDate(user.getStartJoinDate());
			toUpdateUser.setLastJoinDate(user.getLastJoinDate());
			toUpdateUser.setAccessCount(user.getAccessCount());
			toUpdateUser.setRole(user.getRole());
			userRepository.save(toUpdateUser);
		}
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
		User user = getUser(id);
		if (user != null) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public User checkLoginUser(String gmail, String password) {
		return userRepository.findByGmailAndPassword(gmail, password);
	}

}
