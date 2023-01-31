package com.car.category.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.category.repository.StatusRepository;
import com.car.entites.Status;

@Service
public class StatusService {
	@Autowired
	private StatusRepository statusRepository;
	
	public void save(Status fuel) {
		statusRepository.save(fuel);
	}
	
	public List<Status> findAll() {
		return statusRepository.findAll();
	}
	
	public Page<Status> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber,5);
		return statusRepository.findAll(pageable);
		
	}
	public void removeStatusById(int id) {
		statusRepository.deleteById(id);
	}
	
	
	public Optional<Status>getFuelById(int id) {
		return statusRepository.findById(id);
		
	}
}
