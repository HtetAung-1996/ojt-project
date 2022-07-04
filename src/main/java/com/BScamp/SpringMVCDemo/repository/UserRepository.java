package com.BScamp.SpringMVCDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BScamp.SpringMVCDemo.entity.MovieUser;

public interface UserRepository extends JpaRepository<MovieUser, Integer> {

	public MovieUser findByGmailAndPassword(String gmail, String password);
}
