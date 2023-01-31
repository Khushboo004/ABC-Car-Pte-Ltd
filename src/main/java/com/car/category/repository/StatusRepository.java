package com.car.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.car.entites.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{

	@Query("select s FROM Status s where s.status = ?1")
	public Status findByStatus(String status);
}
