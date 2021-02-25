package com.example.TurkcellKrediModulu.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TurkcellKrediModulu.business.abstracts.ICreditApprovalService;
import com.example.TurkcellKrediModulu.business.abstracts.ICreditLimitService;
import com.example.TurkcellKrediModulu.business.abstracts.ICreditService;
import com.example.TurkcellKrediModulu.business.abstracts.ICustomerService;
import com.example.TurkcellKrediModulu.business.abstracts.IEmployeeService;
import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.Employee;

@RestController
@RequestMapping("/api/v1")
public class CreditsController {

	@Autowired
	private ICreditApprovalService creditApprovalService;
	@Autowired
	private ICreditLimitService creditLimitService;
	@Autowired
	private ICreditService creditService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("/credits")
	public List<Credit> getAll(){
		return creditService.getAll();
	}
	
	@GetMapping("/credits/approved")
	public List<Credit> getApproved(){
		return creditService.getApproved();
	}
	
	@GetMapping("/credits/denied")
	public List<Credit> getDenied(){
		return creditService.getDenied();
	}
	
	@GetMapping("/credits/customers/{customer_id}/{credit_id}")
	public Credit myCreditStatus(@PathVariable (value="credit_id") int creditId, @PathVariable (value="customer_id") int customerId) {
		return creditService.myCreditStatus(creditId);
	}
	
	@GetMapping("/credits/customers/{customer_id}") 
	public List<Credit> myCredits(@PathVariable (value="customer_id") int customerId){
		Customer customer = customerService.getByCustomerId(customerId);
		return creditService.creditsByCustomer(customer);
	}
	
	@GetMapping("/credits/employees/{employee_id}")
	public List<Credit> creditsToValidate(@PathVariable (value="employee_id") int employeeId){
		Employee employee = employeeService.findByEmployeeId(employeeId);
		return creditService.creditsByEmployee(employee);
	}
	
	@GetMapping("/credits/employees/{employee_id}/{credit_id}")
	public Credit firstAssessment(@PathVariable (value="employee_id") int employeeId, @PathVariable (value="credit_id") int creditId) {
		Credit credit = creditService.findByCreditId(creditId);
		return creditApprovalService.firstAssessment(credit);
	}
	
	@PostMapping("/credits/customers/{customer_id}/apply")
	public ResponseEntity<Credit> apply(@PathVariable (value="customer_id") int customerId, @RequestBody Credit credit){
		
		Customer customer = customerService.getByCustomerId(customerId);
		creditLimitService.defineCreditLimit(customer);
		
		return ResponseEntity.ok(creditApprovalService.assignCreditToEmployee(customer, credit));
	}
	
	@PutMapping("/credits/customers/{customer_id}/{credit_id}")
	public ResponseEntity<Credit> update(@PathVariable (value="customer_id") int customerId, @PathVariable (value="credit_id") int creditId, @RequestBody Credit credit){
		
		Credit creditToUpdate = creditService.findByCreditId(creditId);
		return ResponseEntity.ok(creditApprovalService.update(creditToUpdate, credit));
	}
	
	@PutMapping("/credits/employees/{employee_id}/{credit_id}")
	public ResponseEntity<Credit> deciideApproval(@PathVariable (value="employee_id") int employeeId, @PathVariable (value="credit_id") int creditId, @RequestBody Credit credit){
		
		Employee employee = employeeService.findByEmployeeId(employeeId);
		Credit creditToUpdate = creditService.findByCreditId(creditId);
		
		return ResponseEntity.ok(creditApprovalService.decideApproval(credit, creditToUpdate, employee));
	}
	
}
