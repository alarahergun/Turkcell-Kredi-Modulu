package com.example.TurkcellKrediModulu.business.abstracts;

import java.util.List;

import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.Employee;

public interface ICreditService { //creditRepository related işlemler

	public List<Credit> getAll();
	public List<Credit> getApproved();
	public List<Credit> getDenied();
	public Credit myCreditStatus(int creditId);
	public List<Credit> creditsByCustomer(Customer customer);
	public List<Credit> creditsByEmployee(Employee employee);
	public Credit findByCreditId(int creditId);
}
