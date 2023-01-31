package com.car.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table
public class CarBidding {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double bidPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="biding_responses", 
	joinColumns =@JoinColumn (name="bid_id"),
	inverseJoinColumns = @JoinColumn(name="response_id"))
	private Set<Response> responses = new HashSet<Response>();
	@ManyToOne()
	private User user;
	public CarBidding() {
		super();
	}
	
	public CarBidding(Long id, double bidPrice, Car car, Set<Response> responses, User user) {
		super();
		this.id = id;
		this.bidPrice = bidPrice;
		this.car = car;
		this.responses = responses;
		this.user = user;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Response> getResponses() {
		return responses;
	}

	public void setResponses(Set<Response> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "CarBidding [id=" + id + ", bidPrice=" + bidPrice + ", car=" + car + ", responses=" + responses
				+ ", user=" + user + "]";
	}

	public void addResponse(Response responses) {
		this.responses.add(responses);
	}
	

	

}
