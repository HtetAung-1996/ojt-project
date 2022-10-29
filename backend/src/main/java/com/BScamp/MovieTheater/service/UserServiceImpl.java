package com.BScamp.MovieTheater.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserStatus;
import com.BScamp.MovieTheater.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder pwEncoder;

	@Override
	public User create(User user) {
		// Check User with Same Gmail Exists
		User tempGmailCheckUser = userRepo.findByGmail(user.getGmail());
		// User with same gmail exists
		if (tempGmailCheckUser != null) {
			return null;
		}
		
		// Create User
		user.setStatus(UserStatus.active);
		user.setPassword(pwEncoder.encode(user.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		return userRepo.save(user);
	}

	@Override
	public User get(int id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public User update(int id, User user) {
		User toUpdateUser = get(id);
		if (toUpdateUser != null) {
			toUpdateUser.setName(user.getName());
			toUpdateUser.setGmail(user.getGmail());
			toUpdateUser.setUpdatedAt(LocalDateTime.now());
			userRepo.save(toUpdateUser);
		}
		return toUpdateUser;
	}

	@Override
	public boolean delete(int id) {
		User user = get(id);
		if (user != null) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public User checkLoginUser(String gmail, String password) {
		User user = userRepo.findByGmail(gmail);
		if (user == null) {
			return null;
		}
		if (pwEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User updateStatus(int id, String status) {
		User toUpdateUser = get(id);
		if (toUpdateUser != null) {
			toUpdateUser.setStatus(UserStatus.valueOf(status));
			toUpdateUser.setUpdatedAt(LocalDateTime.now());
			userRepo.save(toUpdateUser);
		}
		return toUpdateUser;
	}

	@Override
	public List<String> getAllStatus() {
		List<String> userStatusList = new ArrayList<>();
		for (UserStatus status : Arrays.asList(UserStatus.values())) {
			userStatusList.add(status.toString());
		}
		return userStatusList;
	}

}
