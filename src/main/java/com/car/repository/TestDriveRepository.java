package com.car.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.entites.TestDrive;

public interface TestDriveRepository extends JpaRepository<TestDrive, Long>{

	
	@Query("from TestDrive as t where t.car.user.id =:carId")
	public List<TestDrive> findTestDriveByCar(@Param ("carId") Long userId);

	
}