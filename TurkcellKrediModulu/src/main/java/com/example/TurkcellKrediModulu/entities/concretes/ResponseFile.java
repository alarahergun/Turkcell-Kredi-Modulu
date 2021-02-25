package com.example.TurkcellKrediModulu.entities.concretes;

import lombok.Data;

@Data
public class ResponseFile {
	
	private String fileName;
	private String url;
	private String fileType;
	private long size;
	
	public ResponseFile(String fileName, String url, String fileType, long size) {
	    this.fileName = fileName;
	    this.url = url;
	    this.fileType = fileType;
	    this.size = size;
	  }

}
