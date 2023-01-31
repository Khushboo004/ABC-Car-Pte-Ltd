package com.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.entites.User;
import com.car.repository.UserReopsitory;

@Service
public class UserService {

	@Autowired
	private UserReopsitory userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Page<User> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber,5);
		return userRepository.findAll(pageable);
		
	}

	public User getById(Long id) {
		return userRepository.findById(id).get();

	}
	 

	public void delete(User store) {
		userRepository.delete(store);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

}
