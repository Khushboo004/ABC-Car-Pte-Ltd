package com.car.category.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.category.repository.YearReprsitory;
import com.car.entites.Year;

@Service
public class YearService {
	@Autowired
	private YearReprsitory yearReprsitory;
	
	public void save(Year year) {
		yearReprsitory.save(year);
	}
	
	public List<Year> findAll() {
		return yearReprsitory.findAll();
	}
	
	public Page<Year> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber,5);
		return yearReprsitory.findAll(pageable);
		
	}
	
	public void removeYearById(int id) {
		yearReprsitory.deleteById(id);
	}
	
	public Optional<Year>getYearById(int id) {
		return yearReprsitory.findById(id);
		
	}
}
