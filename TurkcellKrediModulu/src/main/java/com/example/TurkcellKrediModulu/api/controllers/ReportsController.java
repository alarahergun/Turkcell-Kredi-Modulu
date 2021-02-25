package com.example.TurkcellKrediModulu.api.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.xml.transform.TransformerConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TurkcellKrediModulu.business.abstracts.IReportService;
import com.example.TurkcellKrediModulu.entities.concretes.Report;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {
	
	@Autowired
	private IReportService reportService;

	@GetMapping("")
	public List<Report> getAll(){
		return reportService.getAll();
	}
	
	@GetMapping("/{report_type}")
	public List<Report> getAllByType(@PathVariable (value="report_type") String reportType){
		return null;
	}
	
	@GetMapping(value = "/daily-pdf-report", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getReportInPdf() throws TransformerConfigurationException, DocumentException, IOException{
		
		ByteArrayInputStream bis = reportService.createPdfReportDaily();
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=dailyreport.pdf");
        
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
	}	
	
	@GetMapping(value = "/daily-pdf-report/{report_id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<Reader> readPdfReport(@PathVariable (value="report_id") int reportId) throws IOException{
		Report report = reportService.getById(reportId);
		return ResponseEntity.ok(reportService.byteArrayToReader(report.getReportFile()));
	}
	
	@GetMapping("/{report_type}/{report_id}/excel-report")
	public ResponseEntity<InputStreamResource> readReportInExcel(@PathVariable (value="report_type") String reportType, @PathVariable(value="report_id") int reportId){
		return null;
	}
	
	@GetMapping("/{report_type}/{report_id}/html-report")
	public ResponseEntity<InputStreamResource> readReportInHtml(@PathVariable (value="report_type") String reportType, @PathVariable(value="report_id") int reportId){
		return null;
	}
}
