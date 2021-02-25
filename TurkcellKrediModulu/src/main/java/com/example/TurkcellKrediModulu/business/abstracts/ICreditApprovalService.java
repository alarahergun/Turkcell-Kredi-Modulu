package com.example.TurkcellKrediModulu.business.abstracts;

import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.Employee;

public interface ICreditApprovalService{

	public Credit assignCreditToEmployee(Customer customer, Credit credit);
	public Credit firstAssessment(Credit credit);
	public Credit decideApproval(Credit credit, Credit creditToUpdate, Employee employee);
	public Credit update(Credit creditToUpdate, Credit credit);
}
