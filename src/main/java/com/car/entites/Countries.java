package com.car.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table

public class Countries {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "countries_id")
	private int id;
	@Column(unique = true)
	private String cname;
	
	public Countries() {
		super();
	}
	
	
	public Countries(int id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Countries [id=" + id + ", cname=" + cname + "]";
	}
	
	
	

}
