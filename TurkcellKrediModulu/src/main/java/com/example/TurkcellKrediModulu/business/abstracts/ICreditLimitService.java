package com.example.TurkcellKrediModulu.business.abstracts;

import com.example.TurkcellKrediModulu.entities.concretes.Customer;

public interface ICreditLimitService{
	
	public Customer defineCreditLimit(Customer customer);
	public boolean existsInDarkList(Customer customer);
	public boolean dateDependent(Customer customer);
	public Customer yearlyDependent(Customer customer);
}
