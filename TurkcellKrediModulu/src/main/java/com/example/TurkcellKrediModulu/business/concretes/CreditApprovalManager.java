package com.example.TurkcellKrediModulu.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TurkcellKrediModulu.business.abstracts.ICreditApprovalService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CreditRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CustomerRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.EmployeeRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.Employee;

@Service
public class CreditApprovalManager implements ICreditApprovalService{

	@Autowired
	private CreditRepository creditRepository;
	@Autowired 
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
	public Credit assignCreditToEmployee(Customer customer, Credit credit) {

		credit.setCreditDate(LocalDate.now());
		credit.setEmployeeId(1); 
		credit.setApproved(-2);
		credit.setCustomerId(customer.getCustomerId());
		credit.setCreditLimit(customer.getCreditLimit());
		return creditRepository.save(credit);
	}

	@Override
	public Credit firstAssessment(Credit credit) {
		
		Customer customer = customerRepository.findByCustomerId(credit.getCustomerId());
		
		if(customer.getCreditLimit() == 0) {
			System.out.println("Bu musteri kredi alamaz, kredi limiti 0!");
			return credit;
		}
		
		if(credit.getCreditAmount() <= customer.getCreditLimit()) {
			System.out.println("Kredi onaya uygundur.");
			return credit;
		}
		
		if(credit.getCreditAmount() > customer.getCreditLimit()) {
			System.out.println("Kredi ust onaya gitmelidir.");
			return credit;
		}
		
		return credit;
	}

	@Override
	public Credit decideApproval(Credit credit, Credit creditToUpdate, Employee employee) {
		
		if(employee.getEmployeeId() != creditToUpdate.getEmployeeId()) {
			System.out.println("Bu krediyi onaylama yetkisine sahip degilsiniz.");
			Employee relatedEmployee = employeeRepository.findByEmployeeId(employee.getEmployeeId());
			System.out.println("İlgili yetkili :" + relatedEmployee.getEmployeeId() + " " + relatedEmployee.getName() + " " + relatedEmployee.getSurname());
			return creditToUpdate;
		}
		
		creditToUpdate.setApproved(credit.getApproved());
		creditToUpdate.setEmployeeId(credit.getEmployeeId());
		
		return creditRepository.save(creditToUpdate);
	}

	@Override
	public Credit update(Credit creditToUpdate, Credit credit) {
		
		creditToUpdate.setCreditAmount(credit.getCreditAmount());
		creditToUpdate.setApproved(-2);
		creditToUpdate.setEmployeeId(1);
		return creditRepository.save(creditToUpdate);
	}
	
	
}
