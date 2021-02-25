package com.example.TurkcellKrediModulu.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TurkcellKrediModulu.business.abstracts.IDarkListService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.DarkListRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Customer;
import com.example.TurkcellKrediModulu.entities.concretes.DarkList;

@Service
public class DarkListManager implements IDarkListService {

	@Autowired
	private DarkListRepository darkListRepository;

	@Override
	public DarkList add(Customer customer) {
		DarkList darkList = new DarkList();
		darkList.setCustomerId(customer.getCustomerId());

		return darkListRepository.save(darkList);
	}

	@Override
	public void remove(Customer customer) {
		
		DarkList darkList = darkListRepository.findByCustomerId(customer.getCustomerId());
		darkListRepository.delete(darkList);
	}

	@Override
	public List<DarkList> getAll() {
		return darkListRepository.findAll();
	}
	
	
	
}
