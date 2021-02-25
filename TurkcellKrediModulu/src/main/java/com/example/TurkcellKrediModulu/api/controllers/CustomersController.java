package com.example.TurkcellKrediModulu.api.controllers;

//import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;

import com.example.TurkcellKrediModulu.business.abstracts.ICustomerService;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;

@RestController
@RequestMapping("/api/v1")
public class CustomersController {

	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getAll(){
		return customerService.getAll();
	}
	
	@GetMapping("/customers/{id}") 
	public Customer getByTcKimlikNo(@PathVariable (value="id") int customerId) {
		return customerService.getByCustomerId(customerId);
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> add(@RequestBody Customer customer){
		return ResponseEntity.ok(customerService.add(customer));
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> update(@PathVariable (value="id") String tcKimlikNo, @RequestBody Customer customer){
		Customer customerToUpdate = customerService.getByTcKimlikNo(tcKimlikNo);
		return ResponseEntity.ok(customerService.update(customerToUpdate, customer));
	}
}
