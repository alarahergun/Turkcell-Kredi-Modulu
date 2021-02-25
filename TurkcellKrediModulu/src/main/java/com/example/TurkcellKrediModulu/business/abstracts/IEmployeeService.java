package com.example.TurkcellKrediModulu.business.abstracts;

import java.util.List;

import com.example.TurkcellKrediModulu.entities.concretes.Employee;

public interface IEmployeeService {

	public List<Employee> getAll();
	public Employee findByEmployeeId(int employeeId);
}
