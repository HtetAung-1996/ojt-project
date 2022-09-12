package com.BScamp.MovieTheater.service;

import java.util.List;

import com.BScamp.MovieTheater.entity.Record;

public interface RecordService {

	public Record saveRecord(Record record);

	public Record getRecord(int id);

	public List<Record> getRecords();

	public Record updateRecord(int id, Record mo);

	public boolean deleteRecord(int id);

}
