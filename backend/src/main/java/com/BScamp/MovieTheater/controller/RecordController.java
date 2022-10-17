package com.BScamp.MovieTheater.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BScamp.MovieTheater.entity.Record;
import com.BScamp.MovieTheater.service.RecordService;

@RestController
public class RecordController {

	@Autowired
	RecordService recordService;

	@GetMapping("/record")
	public List<Record> getRecords(@RequestParam("userID") int userID) {
		return recordService.getAllByUserID(userID);
	}

	@PostMapping("/record/add")
	public ResponseEntity<?> addRecord(@Valid @RequestBody Record record) {
		if (record.getUser().getId() <= 0) {
			return ResponseEntity.badRequest().build();
		}
		if (record.getMovie().getId() <= 0) {
			return ResponseEntity.badRequest().build();
		}
		Record createdRecord = recordService.create(record);
		if (createdRecord == null) {
			return ResponseEntity.badRequest().body("User not found, Movie not found. User role not user");
		}
		return ResponseEntity.ok().body(createdRecord);
	}

}
