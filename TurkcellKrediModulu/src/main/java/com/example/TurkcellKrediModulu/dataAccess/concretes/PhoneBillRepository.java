package com.example.TurkcellKrediModulu.dataAccess.concretes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TurkcellKrediModulu.entities.concretes.PhoneBill;

@Repository
public interface PhoneBillRepository extends JpaRepository<PhoneBill, Integer>{

	public List<PhoneBill> findAllByCustomerId(int customerId);
	public Integer countByLatePayAndCustomerId(boolean latePay, int customerId);
}
