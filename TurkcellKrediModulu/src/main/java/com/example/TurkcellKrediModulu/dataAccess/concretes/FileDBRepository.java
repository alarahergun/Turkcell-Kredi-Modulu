package com.example.TurkcellKrediModulu.dataAccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TurkcellKrediModulu.entities.concretes.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String>{

}
