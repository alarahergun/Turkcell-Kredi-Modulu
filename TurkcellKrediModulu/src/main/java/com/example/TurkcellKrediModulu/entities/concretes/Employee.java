package com.example.TurkcellKrediModulu.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.TurkcellKrediModulu.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="employees")
public class Employee implements IEntity{
	
	@Id
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="level")
	private int level;
}
