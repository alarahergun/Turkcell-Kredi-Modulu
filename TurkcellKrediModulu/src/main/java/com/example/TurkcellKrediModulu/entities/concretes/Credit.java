package com.example.TurkcellKrediModulu.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.TurkcellKrediModulu.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="credits")
public class Credit implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="credit_id")
	private int creditId;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="credit_date")
	private LocalDate creditDate; 
	
	@Column(name="credit_amount")
	private int creditAmount; 

	@Column(name="credit_file_address")
	private String creditFileAddress;
	
	@Column(name="approved")
	private Integer approved = null;
	
	@Column(name="credit_limit")
	private int creditLimit;
	
	@Column(name="employee_id")
	private Integer employeeId = null;
}
