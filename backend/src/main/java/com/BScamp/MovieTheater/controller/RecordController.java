package com.BScamp.MovieTheater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Record addRecord(@RequestBody Record record) {
		return recordService.create(record);
	}

}
