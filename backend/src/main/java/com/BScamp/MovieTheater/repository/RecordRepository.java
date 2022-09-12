package com.BScamp.MovieTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.BScamp.MovieTheater.entity.Record;

@EnableJpaRepositories
public interface RecordRepository extends JpaRepository<Record, Integer> {

}
