package com.car.category.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.category.repository.CountriesRepository;
import com.car.entites.Countries;

@Service
public class CountriesService {
	@Autowired
	private CountriesRepository countriesRepository;
	
	public void save(Countries countries) {
		countriesRepository.save(countries);
	}
	
	public List<Countries> findAll() {
		return countriesRepository.findAll();
	}
	
	public Page<Countries> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber,5);
		return countriesRepository.findAll(pageable);
		
	}
	
	public void removeCountriesById(int id) {
		countriesRepository.deleteById(id);
	}
	
	public Optional<Countries>getCountriesById(int id) {
		return countriesRepository.findById(id);
		
	}
}
