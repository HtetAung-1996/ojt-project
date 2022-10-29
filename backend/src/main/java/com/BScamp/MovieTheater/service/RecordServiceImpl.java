package com.BScamp.MovieTheater.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BScamp.MovieTheater.entity.Movie;
import com.BScamp.MovieTheater.entity.Record;
import com.BScamp.MovieTheater.entity.User;
import com.BScamp.MovieTheater.entity.UserRole;
import com.BScamp.MovieTheater.repository.MovieRepo;
import com.BScamp.MovieTheater.repository.RecordRepo;
import com.BScamp.MovieTheater.repository.UserRepo;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordRepo recordRepo;

	@Autowired
	UserRepo userRepo;
	@Autowired
	MovieRepo movieRepo;

	@Override
	public Record create(Record record) {
		// Check User
		User user = userRepo.findById(record.getUser().getId()).orElse(null);
		if (user == null) {
			return null;
		}
		// Check Movie
		Movie movie = movieRepo.findById(record.getMovie().getId())
				.orElse(null);
		if (movie == null) {
			return null;
		}
		// Create Record Only If user role is user
		if (user.getRole() == UserRole.user) {
			record.setCreatedAt(LocalDateTime.now());
			return recordRepo.save(record);
		}
		return null;
	}

	@Override
	public Record get(int id) {
		return recordRepo.findById(id).orElse(null);
	}

	@Override
	public List<Record> getAll() {
		return recordRepo.findAll();
	}

	@Override
	public Record update(int id, Record rec) {
		Record record = this.get(id);
		if (record == null) {
			return null;
		}
		record.setUser(rec.getUser());
		record.setMovie(rec.getMovie());
		record.setUpdatedAt(LocalDateTime.now());
		recordRepo.save(record);
		return record;
	}

	@Override
	public boolean delete(int id) {
		Record record = this.get(id);
		if (record == null) {
			return false;
		}
		recordRepo.deleteById(id);
		return true;
	}

	@Override
	public List<Record> getAllByUserID(int userID) {
		User user = new User();
		user.setId(userID);
		return recordRepo.findAllByUser(user);
	}

}
