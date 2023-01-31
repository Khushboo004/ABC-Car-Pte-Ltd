package com.car.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.entites.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
