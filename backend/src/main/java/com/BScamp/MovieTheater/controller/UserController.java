package com.BScamp.MovieTheater.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BScamp.MovieTheater.entity.ChangePassword;
import com.BScamp.MovieTheater.entity.JwtResponse;
import com.BScamp.MovieTheater.entity.LoginRequest;
import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.security.JwtUtils;
import com.BScamp.MovieTheater.security.UserDetailsImpl;
import com.BScamp.MovieTheater.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder pwEncoder;

	@PostMapping("/login")
	public ResponseEntity<User> login(
			@Valid @RequestBody LoginRequest lognReq
	) {
		User user = userService
				.checkLoginUser(lognReq.getGmail(), lognReq.getPassword());
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user) {
		User createdUser = userService.create(user);
		if (createdUser == null) {
			return ResponseEntity.badRequest()
					.body("User with same gmail already exists!");
		}
		return ResponseEntity.ok().body(createdUser);
	}

	// @GetMapping("/logout")
	// public Boolean logout(HttpSession session) throws IOException {
	// session.invalidate();
	// return true;
	// }

	@GetMapping("/profile")
	public ResponseEntity<User> getProfile(@RequestParam int id) {
		User user = userService.get(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/changePwd")
	public ResponseEntity<Object> changePassword(
			@Valid @RequestBody ChangePassword changePwd
	) throws IOException {
		User user = userService.get(changePwd.getOri_id());
		if (user == null) {
			return new ResponseEntity<Object>(
					new Exception("Something wrong"), HttpStatus.CONFLICT
			);
		}
		if (!changePwd.getCon_new_pwd().equals(changePwd.getNew_pwd())) {
			return new ResponseEntity<Object>(
					new Exception("Confirm Password does not match"),
					HttpStatus.CONFLICT
			);
		}
		if (!pwEncoder
				.matches(changePwd.getCurrent_pwd(), user.getPassword())) {
			return new ResponseEntity<Object>(
					new Exception("Current Password does not match"),
					HttpStatus.CONFLICT
			);
		}
		userService.updatePwd(user.getId(), changePwd.getNew_pwd());
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/profile/update")
	public ResponseEntity<User> updateProfile(@Valid @RequestBody User user) {
		if (user.getId() <= 0) {
			return ResponseEntity.badRequest().build();
		}
		User updatedUser = userService.update(user.getId(), user);
		if (updatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedUser);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginReq)
			throws IOException {
		// Authenticate
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginReq.getGmail(), loginReq.getPassword()
				)
		);
		// Get User Details
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		return ResponseEntity.ok(
				new JwtResponse(
						jwtUtils.generateJwtToken(auth), userDetails.getId(),
						userDetails.getUsername(), userDetails.getEmail(),
						userDetails.getAuthorities().stream()
								.map(item -> item.getAuthority())
								.collect(Collectors.toList())
				)
		);
	}

}