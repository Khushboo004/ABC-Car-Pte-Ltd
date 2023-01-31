package com.car.category.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.category.repository.TypeRepository;
import com.car.entites.Type;

@Service
public class TypeService {
	@Autowired
	private TypeRepository typeRepository;
	
	public void save(Type type) {
		typeRepository.save(type);
	}
	
	public List<Type> findAll() {
		return typeRepository.findAll();
	}
	
	public Page<Type> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber,5);
		return typeRepository.findAll(pageable);
		
	}
	public void removeTypeById(int id) {
		typeRepository.deleteById(id);
	}
	
	
	public Optional<Type>getTypeById(int id) {
		return typeRepository.findById(id);
		
	}
}
