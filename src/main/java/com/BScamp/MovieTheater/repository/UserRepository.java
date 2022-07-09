package com.BScamp.MovieTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BScamp.MovieTheater.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByGmailAndPassword(String gmail, String password);

}
