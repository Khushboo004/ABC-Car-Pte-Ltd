package com.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entites.TestDrive;
import com.car.repository.TestDriveRepository;

@Service
public class TestDeiveService {
	
	@Autowired
	TestDriveRepository testDriveRepository;
	
	public List<TestDrive>findAll(){
		return testDriveRepository.findAll();
		
	}
	 public void save(TestDrive testDrive) {
		 testDriveRepository.save(testDrive);
	 }
	 
	
}
