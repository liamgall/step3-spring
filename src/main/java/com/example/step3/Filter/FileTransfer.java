package com.example.step3.Filter;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileTransfer {
	private File saveFile;
	private MultipartFile file;
	private String rootPath;
	public FileTransfer(MultipartFile file, String rootPath) {
		this.file = file;
		this.rootPath = rootPath;
	}
	
	
	public void uploadFile() throws IllegalStateException, IOException{
//		saveFile = new File("C:/attatchments/" + file.getOriginalFilename());
		saveFile = new File(rootPath+"resources/attatchments/"+file.getOriginalFilename());
		
		file.transferTo(saveFile);
	}
}
