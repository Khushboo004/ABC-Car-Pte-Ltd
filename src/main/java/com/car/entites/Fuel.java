package com.car.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Fuel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fuel_id")
	private int id;
	@NotBlank(message = "Fuel Can not be Null")
	@Column(unique = true)
	private String fName;
	public Fuel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fuel(int id, @NotBlank(message = "Fuel Can not be Null") String fName) {
		super();
		this.id = id;
		this.fName = fName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	@Override
	public String toString() {
		return "Fuel [id=" + id + ", fName=" + fName + "]";
	}
	
	
}
