package com.car.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "car_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "make_id", referencedColumnName = "make_id")
	private Make make;
	
	@NotBlank(message = "Model cannot be null")
	private String model;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "year_id", referencedColumnName = "year_id")
	private Year year;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countries_id", referencedColumnName = "countries_id")
	private Countries countries;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", referencedColumnName = "type_id")
	private Type type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fuel_id", referencedColumnName = "fuel_id")
	private Fuel fuel;
	private double price;
	private int mileage;
	
	private int cc;
	private String color;
	private String regYear;
	@Column(length = 5000)	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	private String imgShow;
	private String img1;
	private String img2;
	private String img3;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true ,mappedBy = "car")
	private List<TestDrive> testDrives= new ArrayList<TestDrive>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true ,mappedBy = "car")
	private List<CarBidding> carBiddings= new ArrayList<CarBidding>();

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="active_deactive", 
	joinColumns =@JoinColumn (name="car_id"),
	inverseJoinColumns = @JoinColumn(name="status_id"))
	private Set<Status> status = new HashSet<Status>();
	@ManyToOne()
	private User user;

	
	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", countries=" + countries
				+ ", type=" + type + ", fuel=" + fuel + ", price=" + price + ", mileage=" + mileage + ", cc=" + cc
				+ ", color=" + color + ", regYear=" + regYear + ", description=" + description + ", imgShow=" + imgShow
				+ ", img1=" + img1 + ", img2=" + img2 + ", img3=" + img3 + ", testDrives=" + testDrives
				+ ", carBiddings=" + carBiddings + ", status=" + status + ", user=" + user + "]";
	}
	public void addStatus(Status status) {
		this.status.add(status);
	}
	
	
	public boolean hasRole(String statusName) {
		Iterator<Status> iterator = status.iterator();
		while(iterator.hasNext()) {
			Status status = iterator.next();
			if(status.getStatus().equals(statusName)) {
				return true;
			}
		}
		return false;
	}

	
	

}
