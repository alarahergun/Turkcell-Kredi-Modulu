package com.example.TurkcellKrediModulu.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.TurkcellKrediModulu.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "files")
public class FileDB implements IEntity{
	
	  @Id
	  @GeneratedValue(generator = "uuid")
	  @GenericGenerator(name = "uuid", strategy = "uuid2")
	  @Column(name="file_id")
	  private String fileId;

	  @Column(name="file_name")
	  private String fileName;

	  @Column(name="file_type")
	  private String fileType;
	  
	  @Column(name="info_type")
	  private String infoType;

	  @Lob
	  private byte[] data;
	  
	  public FileDB() {
		  
	  }

	  public FileDB(String fileName, String fileType, byte[] data) {
	    this.fileName = fileName;
	    this.fileType = fileType;
	    this.data = data;
	  }

}
