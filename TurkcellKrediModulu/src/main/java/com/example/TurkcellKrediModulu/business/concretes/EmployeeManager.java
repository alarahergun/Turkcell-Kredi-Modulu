package com.example.TurkcellKrediModulu.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TurkcellKrediModulu.business.abstracts.IEmployeeService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.EmployeeRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Employee;

@Service
public class EmployeeManager implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findByEmployeeId(int employeeId) {
		return employeeRepository.findByEmployeeId(employeeId);
	}
	
	

}
