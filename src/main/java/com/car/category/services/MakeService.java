package com.car.category.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.category.repository.MakeRepository;
import com.car.entites.Make;

@Service
public class MakeService {
	@Autowired
	private MakeRepository makeRepository;
	
	public void save(Make make) {
		makeRepository.save(make);
	}
	
	public List<Make> findAll() {
		return makeRepository.findAll();
	}
	
	public Page<Make> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber,5);
		return makeRepository.findAll(pageable);
		
	}
	public void removeMakeById(int id) {
		makeRepository.deleteById(id);
	}
	
	
	public Optional<Make>getMakeById(int id) {
		return makeRepository.findById(id);
		
	}
}
