package com.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entites.CarBidding;
import com.car.repository.CarBiddingRepository;

@Service
public class CarBiddingService {
	
	@Autowired
	CarBiddingRepository carBiddingRepository;
	
	public List<CarBidding>findAll(){
		return carBiddingRepository.findAll();
		
	}
	 public void save(CarBidding carBidding) {
		 carBiddingRepository.save(carBidding);
	 }
	 
	
}
