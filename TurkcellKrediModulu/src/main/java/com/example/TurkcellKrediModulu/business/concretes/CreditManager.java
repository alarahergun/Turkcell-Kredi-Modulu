package com.example.TurkcellKrediModulu.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TurkcellKrediModulu.business.abstracts.ICreditService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CreditRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.Employee;

@Service
public class CreditManager implements ICreditService{

	@Autowired
	private CreditRepository creditRepository;

	@Override
	public List<Credit> getAll() {
		return creditRepository.findAll();
	}

	@Override
	public List<Credit> getApproved() {
		return creditRepository.findAllByApproved(1);
	}

	@Override
	public List<Credit> getDenied() {
		return creditRepository.findAllByApproved(-1);
	}

	@Override
	public Credit myCreditStatus(int creditId) {
		
		Credit myCredit = creditRepository.findByCreditId(creditId);
		
		if(myCredit.getApproved() == 1) {
			System.out.println("Krediniz onaylanmistir.");
			return myCredit;
		}
		else if(myCredit.getApproved() == -1) {
			System.out.println("Krediniz reddedilmistir.");
			System.out.println("İstenen tutar: " + myCredit.getCreditAmount() + " Kredi limitiniz : " + myCredit.getCreditLimit());		
			return myCredit;
		}
		else if(myCredit.getApproved() == 0) {
			System.out.println("Krediniz kredi tutarinda guncelleme bekliyor, giris yaparak guncelleyebilirsiniz.");
			return myCredit;
		}
		else {
			System.out.println("Kredi inceleme asamasinda.");
			return myCredit;
		}
	}

	@Override
	public List<Credit> creditsByCustomer(Customer customer) {
		return creditRepository.findAllByCustomerId(customer.getCustomerId());
	}

	@Override
	public List<Credit> creditsByEmployee(Employee employee) {
		return creditRepository.findAllByEmployeeIdAndApproved(employee.getEmployeeId(), -2);
	}

	@Override
	public Credit findByCreditId(int creditId) {
		return creditRepository.findByCreditId(creditId);
	}
	
	
	
	
	
	
	

	
}
