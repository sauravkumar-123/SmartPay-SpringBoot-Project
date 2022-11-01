package com.Smartpay.FileUpload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Smartpay.Configuration.FileStorageProperties;
import com.Smartpay.Exception.FileNotFoundException;
import com.Smartpay.Exception.FileStorageException;

@Service
public class FileStorageService {

	private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			logger.debug("Failed To Create Directory");
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String[] storeFile(MultipartFile[] files, String aadharlast4digit) {
		String[] resultArr = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			// Normalize file name
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			if (org.apache.commons.lang.StringUtils.isNotEmpty(fileName)) {

				// Check if the file's name contains invalid characters
				if (fileName.contains("..")) {
					throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
				}
				if ((fileName.contains(".jpeg") || fileName.contains(".JPEG"))
						|| (fileName.contains(".jpg") || fileName.contains(".JPG"))
						|| (fileName.contains(".png") || fileName.contains(".PNG"))) {

					try {
						Path targetLocation = this.fileStorageLocation.resolve(fileName + aadharlast4digit);
						Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
						String fileLocation = String.valueOf(targetLocation);
						resultArr[i] = fileLocation;
					} catch (IOException ex) {
						throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
					}

				} else {
					throw new FileStorageException("Allowed Files in .JPG, .JPEG, .PNG Format Only");
				}
			} else {
				throw new FileNotFoundException("Invalid File Received!!");
			}
		}
		return resultArr;
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File not found With " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("File not found " + fileName, ex);
		}
	}
}
