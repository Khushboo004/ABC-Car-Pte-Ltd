package com.car.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class Year {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "year_id")
	private int id;
	@NotBlank(message ="year can't be null")
	@Size(min = 4, max =4, message = "4 characters are allowed")
	@Column(unique = true)
	private String year;
	public Year() {
		super();
	}
	public Year(int id, @NotBlank(message = "year can't be null") String year) {
		super();
		this.id = id;
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Year [id=" + id + ", year=" + year + "]";
	}
	
	
	
	

}
