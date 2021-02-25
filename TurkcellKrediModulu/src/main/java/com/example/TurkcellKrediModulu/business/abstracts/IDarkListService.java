package com.example.TurkcellKrediModulu.business.abstracts;

import java.util.List;

import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.DarkList;

public interface IDarkListService {

	public DarkList add(Customer customer);
	public void remove(Customer customer);
	public List<DarkList> getAll();
}
