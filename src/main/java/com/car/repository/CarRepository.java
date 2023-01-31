package com.car.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.entites.Car;
import com.car.entites.User;

public interface CarRepository extends JpaRepository<Car, Long>{
	
	List<Car> findAllByMake_id(int id);
	List<Car> findAllByYear_id(int id);
	List<Car> findAllByCountries_id(int id);
	List<Car> findAllByFuel_id(int id);
	List<Car> findAllByType_id(int id);
	
	@Query(value = "SELECT c FROM Car c WHERE c.make.mName LIKE '%' || :keyword || '%'"
			+ " OR c.model LIKE '%' || :keyword || '%'"
			+ " OR c.regYear LIKE '%' || :keyword || '%'"
			+ " OR c.price LIKE '%' || :keyword || '%'")
	public List<Car> search(@Param("keyword") String keyword);
	
	@Query("from Car as c where c.user.id =:userId")
	public Page<Car> findCarByUser(@Param ("userId") Long userId,Pageable pageable);
	
	@Query("from Car as c where c.user.id =:userId")
	List<Car> findCarByUser(@Param ("userId")  Long id);
	List<Car> findCarByUser(Optional<User> user);
	List<Car> findCarByUser(User users);

}
