package com.example.TurkcellKrediModulu.business.abstracts;

import java.util.List;

import com.example.TurkcellKrediModulu.entities.concretes.Customer;

public interface ICustomerService {

	public List<Customer> getAll();
	public Customer getByTcKimlikNo(String tcKimlikNo);
	public Customer add(Customer customer);
	public Customer update(Customer customerToUpdate, Customer customer);
	public Customer getByCustomerId(int customerId);
}
