package com.ecommerce.repository;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {

	// check xem file đã tồn tại hay chưa
	public boolean isFileOrFolderExists(String path) throws IOException
	{
		return new File(path).exists();
	}
	//
	public boolean isTypeFileImage(MultipartFile file) {
		return file.getContentType().toLowerCase().contains("image");
	}

	public void createNewMultiPartFile(String path, MultipartFile multipartFile)
			throws IllegalStateException, IOException {
		// write file
		File file = new File(path);
		multipartFile.transferTo(file);
	}

	public String getFormatFile(String input) {
		String[] results = input.split("\\.");
		return results[results.length - 1];
	}
}
