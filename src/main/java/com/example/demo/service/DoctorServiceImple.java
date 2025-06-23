package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DoctorRepo;
import com.example.demo.model.Doctor;

@Service
public class DoctorServiceImple implements DoctorService {
	
	@Autowired
	DoctorRepo doctorRepo;

	@Override
	public Doctor addDoctor(Doctor doctor) {
		
		return  doctorRepo.save(doctor);
	}

	public List<Doctor> getDoctors() {
		// TODO Auto-generated method stub
		return doctorRepo.findAll();
	}

}
