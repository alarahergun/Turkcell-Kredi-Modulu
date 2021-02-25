package com.example.TurkcellKrediModulu.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.TurkcellKrediModulu.business.abstracts.ICreditService;
import com.example.TurkcellKrediModulu.business.abstracts.ICustomerService;
import com.example.TurkcellKrediModulu.business.abstracts.IFileStorageService;
import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.FileDB;
import com.example.TurkcellKrediModulu.entities.concretes.ResponseFile;
import com.example.TurkcellKrediModulu.entities.concretes.ResponseMessage;

@RestController
@RequestMapping("/api/v1")
public class FilesController {
	
	@Autowired
	private IFileStorageService fileStorageService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ICreditService creditService;

	@PostMapping("/customers/{customer_id}")
	public ResponseEntity<ResponseMessage> uploadCustomerFile(@PathVariable (value="customer_id") int customerId, @RequestParam("file") MultipartFile file) {
		
		Customer customer = customerService.getByCustomerId(customerId);
		
		String message = "";
		    
		try {
		      fileStorageService.storeCustomerFile(file, customer);
		      message = "Dosya basariyla yuklendi: " + file.getOriginalFilename();
		      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
		      message = "Dosya yuklenemedi:" + file.getOriginalFilename() + "!";
		      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    }
	}

	@PostMapping("/credits/customers/{customer_id}/{credit_id}/upload")
	public ResponseEntity<ResponseMessage> uploadCreditFile(@PathVariable (value="customer_id") int customerId, @PathVariable (value="credit_id") int creditId, @RequestParam("file") MultipartFile file) {
		
		Credit credit = creditService.findByCreditId(creditId);
		
		String message = "";
	    
		try {
	      fileStorageService.storeCreditFile(file, credit);
	      message = "Dosya basariyla yuklendi: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Dosya yuklenemedi:" + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	
	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		
		List<ResponseFile> files = fileStorageService.getAllFiles().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getFileId())
	          .toUriString();

	      return new ResponseFile(
	          dbFile.getFileName(),
	          fileDownloadUri,
	          dbFile.getFileType(),
	          dbFile.getData().length);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }
	
	@GetMapping("/files/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable (value="id") String fileId) {
	    FileDB fileDB = fileStorageService.getFile(fileId);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
	        .body(fileDB.getData());
	  }
}
