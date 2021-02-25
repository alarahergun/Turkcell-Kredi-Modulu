package com.example.TurkcellKrediModulu.dataAccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TurkcellKrediModulu.entities.concretes.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{

	Report findByReportId(int reportId);
}
