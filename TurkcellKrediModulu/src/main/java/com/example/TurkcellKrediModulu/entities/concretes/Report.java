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
@Table(name="reports")
public class Report implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="report_id")
	private int reportId;
	
	@Column(name="report_type")
	private String reportType;
	
	@Column(name="report_date")
	private LocalDate reportDate;
	
	@Column(name="report_file")
	private byte[] reportFile;
}
