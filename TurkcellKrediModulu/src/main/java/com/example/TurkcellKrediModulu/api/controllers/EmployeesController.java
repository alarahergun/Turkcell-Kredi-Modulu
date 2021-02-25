package com.example.TurkcellKrediModulu.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TurkcellKrediModulu.business.abstracts.IEmployeeService;
import com.example.TurkcellKrediModulu.entities.concretes.Employee;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("")
	public List<Employee> getAll(){
		return employeeService.getAll();
	}
}
