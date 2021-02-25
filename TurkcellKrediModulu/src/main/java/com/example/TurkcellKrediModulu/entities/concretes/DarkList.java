package com.example.TurkcellKrediModulu.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.TurkcellKrediModulu.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="dark_list")
public class DarkList implements IEntity{

	@Id
	@Column(name="customer_id")
	private int customerId;	
}
