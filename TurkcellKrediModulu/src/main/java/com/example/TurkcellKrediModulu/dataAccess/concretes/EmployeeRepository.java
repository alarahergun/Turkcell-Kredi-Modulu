package com.example.TurkcellKrediModulu.dataAccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TurkcellKrediModulu.entities.concretes.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	public Employee findByEmployeeId(int employeeId);
	
}
