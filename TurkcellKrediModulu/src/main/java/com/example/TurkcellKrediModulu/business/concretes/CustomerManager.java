package com.example.TurkcellKrediModulu.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TurkcellKrediModulu.business.abstracts.ICustomerService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CustomerRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;

@Service
public class CustomerManager implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getByTcKimlikNo(String tcKimlikNo) {

		if(customerRepository.existsByTcKimlikNo(tcKimlikNo)) {
			return customerRepository.findByTcKimlikNo(tcKimlikNo);
		}
		
		System.out.println("Musteri veritabaninda kayitli degil.");
		return null;
	}
	
	@Override
	public Customer getByCustomerId(int customerId) {
		return customerRepository.findByCustomerId(customerId); //exception yazılacak buraya
	}
	
	@Override
	public Customer add(Customer customer) {
		
		if(customerRepository.existsByTcKimlikNo(customer.getTcKimlikNo())) {
			System.out.println("Musteri daha once kaydedilmis.");
			Customer oldCustomer = customerRepository.findByTcKimlikNo(customer.getTcKimlikNo());
			return oldCustomer;
		}
		
		customer.setSubscriptionDate(LocalDate.now());
		return customerRepository.save(customer);
	}

	@Override
	public Customer update(Customer customerToUpdate, Customer customer) {
		
		customerToUpdate.setAddress(customer.getAddress());
		customerToUpdate.setBirthDate(customer.getBirthDate());
		customerToUpdate.setCustomerType(customer.getCustomerType());
		customerToUpdate.setLegalPursuit(customer.isLegalPursuit());
		customerToUpdate.setMsisdn(customer.getMsisdn());
		customerToUpdate.setName(customer.getName());
		customerToUpdate.setSubscriptionDate(customer.getSubscriptionDate());
		customerToUpdate.setSurname(customer.getSurname());
		customerToUpdate.setTcKimlikNo(customer.getTcKimlikNo());
		
		return customerRepository.save(customerToUpdate);
	}
	
	

	
	
	
	
	
}
