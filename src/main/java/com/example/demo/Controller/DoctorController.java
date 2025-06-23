package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorPdf;
import com.example.demo.service.DoctorServiceImple;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class DoctorController {
	
	@Autowired
	DoctorServiceImple doctorServiceImple;
	
	@PostMapping("/addDoctor")
	public Doctor addDoctor(@RequestBody Doctor doctor) {
		return doctorServiceImple.addDoctor(doctor);
		
	}
	
	@GetMapping("/generateDoctorPdf")
	public void exportDoctor(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("Application/Pdf");
		
		String headerKey="Content-Desposition";
		String headerVal="attachment; filename=Doctor.pdf";
		
		response.addHeader(headerKey, headerVal);
		
		List<Doctor> doctorList=doctorServiceImple.getDoctors();
		
		DoctorPdf docPdf=new DoctorPdf(doctorList);
		
		docPdf.export(response);
		
	}
}
