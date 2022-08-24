package com.BScamp.MovieTheater.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BScamp.MovieTheater.entity.Record;
import com.BScamp.MovieTheater.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordRepository recordRepo;

	@Override
	public Record saveRecord(Record record) {
		record.setCreatedAt(LocalDateTime.now());
		return recordRepo.save(record);
	}

	@Override
	public Record getRecord(int id) {
		return recordRepo.findById(id).orElse(null);
	}

	@Override
	public List<Record> getRecords() {
		return recordRepo.findAll();
	}

	@Override
	public Record updateRecord(int id, Record rec) {
		Record record = getRecord(id);
		if (record != null) {
			record.setUser(rec.getUser());
			record.setMovie(rec.getMovie());
			record.setUpdatedAt(LocalDateTime.now());
			recordRepo.save(record);
		}
		return record;
	}

	@Override
	public boolean deleteRecord(int id) {
		recordRepo.deleteById(id);
		return true;
	}

}
