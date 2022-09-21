package com.BScamp.MovieTheater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.BScamp.MovieTheater.entity.Record;
import com.BScamp.MovieTheater.entity.User;

@EnableJpaRepositories
public interface RecordRepo extends JpaRepository<Record, Integer> {
	
	public List<Record> findAllByUser(User user);

}
