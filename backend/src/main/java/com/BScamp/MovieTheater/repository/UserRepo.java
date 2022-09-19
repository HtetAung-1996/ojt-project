package com.BScamp.MovieTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BScamp.MovieTheater.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByGmail(String gmail);

	public User findByName(String name);

}
