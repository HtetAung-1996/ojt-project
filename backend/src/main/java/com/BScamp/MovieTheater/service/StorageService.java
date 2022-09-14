package com.BScamp.MovieTheater.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public String saveFile(MultipartFile file, String fileType);

	public byte[] load(String filePath);

	public boolean deleteFile(String filePath);

	public String updateFile(MultipartFile file, String fileType, String filePath);

}
