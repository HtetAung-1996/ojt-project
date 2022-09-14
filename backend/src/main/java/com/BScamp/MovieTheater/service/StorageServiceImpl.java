package com.BScamp.MovieTheater.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	private final Path storagePath;

	private final Random random = new Random();

	@Autowired
	public StorageServiceImpl() throws IOException {

		Path storagePath = Paths.get("").resolve("src").resolve("main").resolve("resources").resolve("static")
				.resolve("images");
		if (!Files.exists(storagePath)) {
			Files.createDirectories(storagePath);
		}
		this.storagePath = storagePath;
	}

	@Override
	public String saveFile(MultipartFile file, String fileType) {

		String filePath = null;

		try {
			String fileName = random.nextInt(999999) + "_" + StringUtils.cleanPath(file.getOriginalFilename());
			Files.copy(file.getInputStream(), this.storagePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			filePath = "/image/" + (fileType == "image/jpg" ? "jpg" : "png") + "/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath;

	}

	@Override
	public byte[] load(String fileName) {

		byte[] retBytes = null;

		try {
			Path file = this.storagePath.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				retBytes = StreamUtils.copyToByteArray(resource.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retBytes;

	}

	@Override
	public boolean deleteFile(String filePath) {

		try {
			Files.delete(this.storagePath.resolve(filePath));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public String updateFile(MultipartFile file, String fileType, String filePath) {

		String retfilePath = null;

		try {
			if (filePath != null && filePath != "") {
				try {
					Files.delete(this.storagePath.resolve(filePath));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String fileName = random.nextInt(999999) + "_" + StringUtils.cleanPath(file.getOriginalFilename());
			Files.copy(file.getInputStream(), this.storagePath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			retfilePath = "/image/" + (fileType == "image/jpg" ? "jpg" : "png") + "/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retfilePath;

	}

}
