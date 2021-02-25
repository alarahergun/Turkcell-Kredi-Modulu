package com.example.TurkcellKrediModulu.dataAccess.concretes;


import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TurkcellKrediModulu.entities.concretes.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findByTcKimlikNo(String tcKimlikNo);
	public boolean existsByTcKimlikNo(String tcKimlikNo);
	
	public Customer findByCustomerId(int customerId);
	public int countBySubscriptionDate(LocalDate subscriptionDate);
}
