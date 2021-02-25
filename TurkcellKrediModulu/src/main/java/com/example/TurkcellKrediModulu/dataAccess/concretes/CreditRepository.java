package com.example.TurkcellKrediModulu.dataAccess.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TurkcellKrediModulu.entities.concretes.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer>{

	public boolean existsByCustomerId(int customerId);
	public ArrayList<Credit> findAllByCustomerIdAndApprovedOrderByCreditDateDesc(int customerId, int approved);
	public Credit findByCreditId(int creditId);
	public List<Credit> findAllByApproved(int approved);
	public List<Credit> findAllByCustomerId(int customerId);
	public boolean existsByCustomerIdAndApproved(int customerId, int approved);
	public List<Credit> findAllByCustomerIdAndApproved(int customerId, int approved);
	public List<Credit> findAllByCreditDateAndApproved(LocalDate creditDate, int approved);
	public List<Credit> findAllByEmployeeIdAndApproved(int employeeId, int approved);
}
