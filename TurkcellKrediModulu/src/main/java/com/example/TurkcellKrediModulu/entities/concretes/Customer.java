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
@Table(name="customers")
public class Customer implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="tc_kimlik_no")
	private String tcKimlikNo;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="msisdn")
	private String msisdn;
	
	@Column(name="address", length = 1024)
	private String address;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@Column(name="customer_type")
	private String customerType;

	@Column(name="subscription_date")
	private LocalDate subscriptionDate;
	
	@Column(name="credit_limit")
	private int creditLimit;
	
	@Column(name="legal_pursuit")
	private boolean legalPursuit = false;
	
	@Column(name="id_file")
	private String idFileAddress;
}