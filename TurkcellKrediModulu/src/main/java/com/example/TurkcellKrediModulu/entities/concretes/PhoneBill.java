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
@Table(name="phone_bills")
public class PhoneBill implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="phone_bill_id")
	private int phoneBillId;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="bill_amount")
	private double billAmount;
	
	@Column(name="bill_start_date")
	private LocalDate billStartDate;
	
	@Column(name="bill_finish_date")
	private LocalDate billFinishDate;
	
	@Column(name="bill_paid_date")
	private LocalDate billPaidDate;
	
	@Column(name="late_pay")
	private boolean latePay;
}
