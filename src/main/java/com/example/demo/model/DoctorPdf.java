package com.example.demo.model;

import java.awt.Color;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.PublicKey;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class DoctorPdf {
	List<Doctor> doctors;

	public DoctorPdf(List<Doctor> doctors) {
		super();
		this.doctors = doctors;
	}
	
	public void writetableHeader(PdfPTable pdfTable) {
		
		PdfPCell cell=new PdfPCell();
		cell.setPadding(5);
		cell.setBackgroundColor(Color.orange);
		
		cell.setPhrase(new Phrase(" Doctor ID"));
		pdfTable.addCell(cell);
		
		cell.setPhrase(new Phrase(" Name"));
		pdfTable.addCell(cell);
		
		cell.setPhrase(new Phrase(" Salary"));
		pdfTable.addCell(cell);
		
		
		cell.setPhrase(new Phrase(" Experience"));
		pdfTable.addCell(cell);
		
		cell.setPhrase(new Phrase(" location"));
		pdfTable.addCell(cell);
				
	}
	
	public void writetableData(PdfPTable table) {
		for( Doctor doctor: doctors) {
			table.addCell(String.valueOf(doctor.getId()));
			table.addCell(doctor.getName());
			table.addCell(String.valueOf(doctor.getSalary()));
			table.addCell(String.valueOf(doctor.getExperience()));
			table.addCell(doctor.getLocation());
			//table.addCell(String.valueOf(doctor.getSalary()));
			
			
		}
		
	}
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document=new Document(PageSize.A4);
		
		PdfWriter.getInstance(document,response.getOutputStream());
		
		document.open();
		
		PdfPTable table=new PdfPTable(5);
		table.setWidthPercentage(100);
		writetableHeader(table);
		writetableData(table);
		
		document.add(table);
		document.close();
	}
	

}
