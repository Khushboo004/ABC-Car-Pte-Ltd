package com.car.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.car.entites.Role;




public interface RoleRepository extends JpaRepository<Role, Integer>{

	@Query("select r FROM Role r where r.name = ?1")
	public Role findByName(String name);
}