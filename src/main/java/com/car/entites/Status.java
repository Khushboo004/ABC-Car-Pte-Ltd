package com.car.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table 
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private int id;
	private String status;
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Status(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	@Override
	public String toString() {
		return status ;
	}

	
	
	
	
	

}
