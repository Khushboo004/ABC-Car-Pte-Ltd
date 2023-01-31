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
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "type_id")
	private int id;
	@NotBlank(message = "Type Can not be Null")
	@Column(unique = true)
	private String tName;
	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Type(int id, @NotBlank(message = "Type Can not be Null") String tName) {
		super();
		this.id = id;
		this.tName = tName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", tName=" + tName + "]";
	}
	
	
}


