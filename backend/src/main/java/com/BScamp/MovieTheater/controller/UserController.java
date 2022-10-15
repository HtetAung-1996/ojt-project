package com.BScamp.MovieTheater.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BScamp.MovieTheater.entity.JwtResponse;
import com.BScamp.MovieTheater.entity.LoginRequest;
import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
import com.BScamp.MovieTheater.security.JwtUtils;
import com.BScamp.MovieTheater.security.UserDetailsImpl;
import com.BScamp.MovieTheater.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<User> login(@Valid @RequestBody LoginRequest lognReq, HttpSession session)
			throws IOException {
		User user = userService.checkLoginUser(lognReq.getGmail(), lognReq.getPassword());
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody User user, HttpSession session) throws IOException {
		user.setRole(UserRole.user);
		user.setStartJoinDate(LocalDate.now());
		User createdUser = userService.create(user);
		session.setAttribute("loginUser", createdUser);
		return ResponseEntity.ok().body(createdUser);
	}

	@GetMapping("/logout")
	public Boolean logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
		return true;
	}

	@GetMapping("/profile")
	public User getProfile(@RequestParam int id) throws IOException {
		return userService.get(id);
	}

	@PutMapping("/profile/update")
	public User getProfile(@RequestBody User user) throws IOException {
		return userService.update(user.getId(), user);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest lognReq) throws IOException {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(lognReq.getGmail(), lognReq.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

}