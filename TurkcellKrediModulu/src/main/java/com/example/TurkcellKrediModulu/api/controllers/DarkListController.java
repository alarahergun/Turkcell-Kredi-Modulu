package com.example.TurkcellKrediModulu.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TurkcellKrediModulu.business.abstracts.IDarkListService;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.DarkList;

@RestController
@RequestMapping("/api/v1/dark-list")
public class DarkListController {

	@Autowired
	private IDarkListService darkListService;

	@GetMapping("")
	public List<DarkList> getAll(){
		return darkListService.getAll();
	}
	
	@PostMapping("")
	public ResponseEntity<DarkList> add(@RequestBody Customer customer){
		return ResponseEntity.ok(darkListService.add(customer));
	}
	
	
	
	
	
}
