package com.BScamp.MovieTheater.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BScamp.MovieTheater.entity.LoginRequest;
import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
import com.BScamp.MovieTheater.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<User> login(
			@Valid @RequestBody LoginRequest lognReq, HttpSession session
	) throws IOException {
		User user = userService
				.checkLoginUser(lognReq.getGmail(), lognReq.getPassword());
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(
			@Valid @RequestBody User user, HttpSession session
	) throws IOException {
		user.setRole(UserRole.user);
		user.setStartJoinDate(LocalDate.now());
		User createdUser = userService.create(user);
		if (createdUser == null) {
			return ResponseEntity.badRequest().body("User with same gmail already exists!");
		}
		session.setAttribute("loginUser", createdUser);
		return ResponseEntity.ok().body(createdUser);
	}

	@GetMapping("/logout")
	public Boolean logout(HttpSession session, HttpServletResponse response)
			throws IOException {
		session.invalidate();
		return true;
	}

	@GetMapping("/profile")
	public ResponseEntity<User> getProfile(@RequestParam int id)
			throws IOException {
		User user = userService.get(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/profile/update")
	public ResponseEntity<User> updateProfile(@Valid @RequestBody User user)
			throws IOException {
		if (user.getId() <= 0) {
			return ResponseEntity.badRequest().build();
		}
		User updatedUser = userService.update(user.getId(), user);
		if (updatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedUser);
	}

}