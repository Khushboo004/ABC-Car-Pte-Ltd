package com.car.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.car.entites.Response;


public interface ResponseRepository extends JpaRepository<Response, Integer>{
	
	@Query("select r FROM Response r where r.response = ?1")
	public Response findByResponse(String response);

}
