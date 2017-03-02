package com.example.step3.model;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileTransfer {
	private File saveFile;
	private MultipartFile file;
	
	public FileTransfer(MultipartFile file) {
		this.file = file;
	}
	
	
	public void uploadFile() throws IllegalStateException, IOException{
		saveFile = new File("C:/attatchments/" + file.getOriginalFilename());
		file.transferTo(saveFile);
	}
}
