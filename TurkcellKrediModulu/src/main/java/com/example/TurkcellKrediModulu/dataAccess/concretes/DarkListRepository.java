package com.example.TurkcellKrediModulu.dataAccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TurkcellKrediModulu.entities.concretes.DarkList;

@Repository
public interface DarkListRepository extends JpaRepository<DarkList, Integer>{

	public boolean existsByCustomerId(int customerId);
	public DarkList findByCustomerId(int customerId);
}
