package com.car.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.entites.User;

public interface UserReopsitory extends JpaRepository<User, Long>{


	
	@Query("select u FROM User u WHERE u.email = :email")
	public User getUserByUsername(@Param("email") String email);
	
	@Query("select name FROM User u WHERE u.email = :email")
	public User getUserByName(@Param("email") String email);
	
	

}
