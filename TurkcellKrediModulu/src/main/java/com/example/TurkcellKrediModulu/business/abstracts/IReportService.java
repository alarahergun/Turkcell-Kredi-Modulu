package com.example.TurkcellKrediModulu.business.abstracts;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.xml.transform.TransformerConfigurationException;

import com.example.TurkcellKrediModulu.entities.concretes.Report;
import com.itextpdf.text.DocumentException;

public interface IReportService {
	public Report getById(int reportId);
	public List<Report> getAll();
	public ByteArrayInputStream createPdfReportDaily() throws FileNotFoundException, DocumentException, TransformerConfigurationException, IOException;
	public ByteArrayInputStream createPdfReportWeekly() throws FileNotFoundException, DocumentException, TransformerConfigurationException;
	public Reader byteArrayToReader(byte[] pdfDocument) throws IOException;
}
