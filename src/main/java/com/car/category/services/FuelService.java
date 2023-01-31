package com.car.category.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.category.repository.FuelRepository;
import com.car.entites.Fuel;

@Service
public class FuelService {
	@Autowired
	private FuelRepository fuelRepository;
	
	public void save(Fuel fuel) {
		fuelRepository.save(fuel);
	}
	
	public List<Fuel> findAll() {
		return fuelRepository.findAll();
	}
	
	public Page<Fuel> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber,5);
		return fuelRepository.findAll(pageable);
		
	}
	public void removeFuelById(int id) {
		fuelRepository.deleteById(id);
	}
	
	
	public Optional<Fuel>getFuelById(int id) {
		return fuelRepository.findById(id);
		
	}
}
