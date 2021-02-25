package com.example.TurkcellKrediModulu.business.concretes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;
import javax.xml.transform.TransformerConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TurkcellKrediModulu.business.abstracts.IReportService;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CreditRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.CustomerRepository;
import com.example.TurkcellKrediModulu.dataAccess.concretes.ReportRepository;
import com.example.TurkcellKrediModulu.entities.concretes.Credit;
import com.example.TurkcellKrediModulu.entities.concretes.Report;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ReportManager implements IReportService{

	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CreditRepository creditRepository;
	
	@Override
	public List<Report> getAll() {
		return reportRepository.findAll();
	}

	@Override
	public ByteArrayInputStream createPdfReportDaily() throws DocumentException, TransformerConfigurationException, IOException {
		
		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		LocalDate today = LocalDate.now();
		PdfWriter.getInstance(document, outputStream);
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		String daily = "Daily Report for new Customers and Approved Credit Entry Amounts : ";
		
		int newCustomers = customerRepository.countBySubscriptionDate(today);
		List<Credit> creditsPerDay = creditRepository.findAllByCreditDateAndApproved(today.minusDays(1), 1);
		int totalCredit = 0;
		for(Credit i:creditsPerDay) {
			totalCredit += i.getCreditAmount();
		}
		
		Chunk chunk = new Chunk(daily + today + "\n" + "--> " + newCustomers +"\n" + "--> " + totalCredit, font);
		document.add(chunk);
		
		document.close();
		ByteArrayInputStream dailyReport = new ByteArrayInputStream(outputStream.toByteArray());
		
		Report report = new Report();
		report.setReportDate(today);
		report.setReportType("daily_report");
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] tmp = new byte[4096];
		int ret = 0;

		while((ret = dailyReport.read(tmp)) > 0)
		{
		    bos.write(tmp, 0, ret);
		}

		byte[] myArray = bos.toByteArray();
		report.setReportFile(myArray);
		reportRepository.save(report);
	
		return dailyReport;
	}
	
	@Override
	public ByteArrayInputStream createPdfReportWeekly() throws FileNotFoundException, DocumentException, TransformerConfigurationException {
		
		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		LocalDate today = LocalDate.now();
		PdfWriter.getInstance(document, outputStream);
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		String daily = "Weekly Report for new Customers and Approved Credit Entry Amounts : ";
		
		int newCustomers = customerRepository.countBySubscriptionDate(today);
		List<Credit> creditsPerDay = creditRepository.findAllByCreditDateAndApproved(today.minusDays(1), 1);
		int totalCredit = 0;
		for(Credit i:creditsPerDay) {
			totalCredit += i.getCreditAmount();
		}
		
		Chunk chunk = new Chunk(daily + today + "\n" + "--> " + newCustomers +"\n" + "--> " + totalCredit, font);
		document.add(chunk);
		
		document.close();
		
		Report report = new Report();
		report.setReportDate(today);
		report.setReportType("daily_report");
		
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	@Override
	public Reader byteArrayToReader(byte[] pdfDocument) throws IOException {
		
	    Reader targetReader = new StringReader(new String(pdfDocument));
	    targetReader.close();
	    
	    return targetReader;
	}

	@Override
	public Report getById(int reportId) {
		return reportRepository.findByReportId(reportId);
	}
	
}
