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
public class Make {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "make_id")
	private int id;
	@NotBlank(message = "Maker Can not be Null")
	@Column(unique = true)
	private String mName;
	public Make() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Make(int id, @NotBlank(message = "Maker Can not be Null") String mName) {
		super();
		this.id = id;
		this.mName = mName;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	@Override
	public String toString() {
		return "Make [id=" + id + ", mName=" + mName + "]";
	}
	
	
}
