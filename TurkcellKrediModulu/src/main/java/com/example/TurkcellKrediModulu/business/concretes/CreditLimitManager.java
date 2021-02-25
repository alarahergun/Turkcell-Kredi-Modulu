package com.example.TurkcellKrediModulu.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TurkcellKrediModulu.business.abstracts.ICreditLimitService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CreditRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CustomerRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.DarkListRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.PhoneBillRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.PhoneBill;


@Service
public class CreditLimitManager implements ICreditLimitService{

	@Autowired
	private DarkListRepository darkListRepository;
	@Autowired
	private CreditRepository creditRepository;
	@Autowired 
	private PhoneBillRepository phoneBillRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer defineCreditLimit(Customer customer) {
		
		if(existsInDarkList(customer)) {
			customer.setCreditLimit(0);
			System.out.println("Musteri kara listede.");
			return customerRepository.save(customer);
		}
		
		if(customer.isLegalPursuit()) {
			customer.setCreditLimit(0);
			System.out.println("Musteri yasal takipte.");
			return customerRepository.save(customer);
		}
		
		if(dateDependent(customer)) {
			customer.setCreditLimit(0);
			return customerRepository.save(customer);
		}
		
		List<PhoneBill> phoneBillOfCustomer = phoneBillRepository.findAllByCustomerId(customer.getCustomerId());
		if(phoneBillOfCustomer.size() == 0){ 
			customer.setCreditLimit(2000);
			return customerRepository.save(customer);
		}
		
		int countOfEarlyBills = phoneBillRepository.countByLatePayAndCustomerId(false, customer.getCustomerId());
		LocalDate now = LocalDate.now();
		int yearsOfSubscription = now.getYear() - (customer.getSubscriptionDate().getYear());
		
		customer.setCreditLimit(2000 + 100 * countOfEarlyBills + 200 * yearsOfSubscription);
		
		if(creditRepository.existsByCustomerIdAndApproved(customer.getCustomerId(), 1)) {
			return yearlyDependent(customer);
		}
 		
		return customerRepository.save(customer);
	}
	
	@Override
	public boolean existsInDarkList(Customer customer) {
		if(darkListRepository.existsByCustomerId(customer.getCustomerId())){
			return true;
		}
		return false;
	}

	@Override
	public boolean dateDependent(Customer customer) {
		
		if(creditRepository.existsByCustomerIdAndApproved(customer.getCustomerId(), 1)) {
			
			List<Credit> creditsOfCustomer = creditRepository.findAllByCustomerIdAndApproved(customer.getCustomerId(), 1);
			
			Credit lastCreditOfCustomer = creditsOfCustomer.get(0);
			LocalDate end = LocalDate.now();
			LocalDate start = lastCreditOfCustomer.getCreditDate();
			
			int monthlyCredit = end.getDayOfYear() - start.getDayOfYear();
			
			if(monthlyCredit < 30) {
				System.out.println("Son kredi alimindan 1 ay sure gecmesi gerekmektedir.");
				System.out.println("Son kredi alim tarihi : " + start);
				System.out.println(monthlyCredit);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public Customer yearlyDependent(Customer customer) {
		
		List<Credit> creditsOfBefore = creditRepository.findAllByCustomerIdAndApproved(customer.getCustomerId(), 1);
		int yearlyAmountOfCreditAmount = 0;
	
		for(Credit i : creditsOfBefore) {
			if(i.getCreditDate().isAfter(LocalDate.now().minusYears(1))) {
				yearlyAmountOfCreditAmount += i.getCreditAmount();
			}
		}
		
		if(yearlyAmountOfCreditAmount + customer.getCreditLimit() > 10000) {
			customer.setCreditLimit(10000 - yearlyAmountOfCreditAmount);
		}
		
		return customerRepository.save(customer);
	}
	

	
}
