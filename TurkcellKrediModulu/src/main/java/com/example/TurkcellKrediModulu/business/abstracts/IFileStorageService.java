package com.example.TurkcellKrediModulu.business.abstracts;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.FileDB;

public interface IFileStorageService {

	public FileDB storeCustomerFile(MultipartFile file, Customer customer) throws IOException;
	public FileDB storeCreditFile(MultipartFile file, Credit credit) throws IOException;
	public FileDB getFile(String id);
	public Stream<FileDB> getAllFiles();
}
