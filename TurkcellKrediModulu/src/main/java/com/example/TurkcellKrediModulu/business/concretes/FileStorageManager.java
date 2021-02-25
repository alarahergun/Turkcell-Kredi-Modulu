package com.example.TurkcellKrediModulu.business.concretes;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.TurkcellKrediModulu.business.abstracts.IFileStorageService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CreditRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CustomerRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.FileDBRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.FileDB;

@Service
public class FileStorageManager implements IFileStorageService{

	@Autowired
	private FileDBRepository fileDBRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CreditRepository creditRepository;

	@Override
	public FileDB storeCustomerFile(MultipartFile file, Customer customer) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

		FileDB.setInfoType("customerIDFile");
	
		fileDBRepository.save(FileDB);
		customer.setIdFileAddress("http://localhost:8080/api/v1/files/" + FileDB.getFileId());
		customerRepository.save(customer);
		return FileDB;
	}
	
	@Override
	public FileDB storeCreditFile(MultipartFile file, Credit credit) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

		FileDB.setInfoType("creditFile");
		fileDBRepository.save(FileDB);
		credit.setCreditFileAddress("http://localhost:8080/api/v1/files/" + FileDB.getFileId());
		creditRepository.save(credit);
		return FileDB;
	}

	@Override
	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	@Override
	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}

}
