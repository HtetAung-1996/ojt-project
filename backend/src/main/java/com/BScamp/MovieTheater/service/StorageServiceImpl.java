package com.BScamp.MovieTheater.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	@Override
	public String saveFile(MultipartFile file, String fileType) {

		String filePath = null;

		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path uploadPath = Paths.get("").resolve("src").resolve("main").resolve("resources").resolve("static")
					.resolve("images");
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
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
			Path uploadPath = Paths.get("").resolve("src").resolve("main").resolve("resources").resolve("static")
					.resolve("images");
			Path file = uploadPath.resolve(fileName);
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

}
