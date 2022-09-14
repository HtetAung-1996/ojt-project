package com.BScamp.MovieTheater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.BScamp.MovieTheater.service.RecordService;

@RestController
public class RecordController {

	@Autowired
	RecordService recordService;

}
