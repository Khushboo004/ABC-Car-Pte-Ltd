package com.car.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entites.Car;
import com.car.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	CarRepository carRepository;
	
	public List<Car> getAllCar(){
		return carRepository.findAll();
		
	}
	 public void save(Car car) {
		 carRepository.save(car);
	 }
	
	 public void removeCar(Long id) {
		 carRepository.deleteById(id);
	 }
	 
	 public Optional<Car> updateCar(Long id) {
		return  carRepository.findById(id);
	 } 
	 
	 public List<Car> getAllCarByMake_id(int id){
		 return carRepository.findAllByMake_id(id);
	 }
	 
	 public List<Car> getAllCarByYear_id(int id){
		 return carRepository.findAllByYear_id(id);
	 }
	 
	public List<Car> getAllCarByCountries_id(int id) {
		return carRepository.findAllByCountries_id(id);
	}
	 
	public List<Car> getAllCarByFuel_id(int id) {
		return carRepository.findAllByFuel_id(id);
	}
	public List<Car> getAllCarByType_id(int id) {
		return carRepository.findAllByType_id(id);
	}

	public List<Car> search(String keyword) {
		return carRepository.search(keyword);
	}

}
