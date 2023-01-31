package com.car.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
@Entity
@Table
public class TestDrive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	private String postcode;
	private String preferredDate;
	@Column(length = 5000)
	public String comments;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id")
	private Car car;
	@ManyToOne()
	private User user;
	public TestDrive() {
		super();
	}

	
	public TestDrive(Long id, String firstName, String lastName, String phone,
			@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") String email, String postcode, String preferredDate,
			String comments, Car car, User user) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.postcode = postcode;
		this.preferredDate = preferredDate;
		this.comments = comments;
		this.car = car;
		this.user = user;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(String preferredDate) {
		this.preferredDate = preferredDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	@Override
	public String toString() {
		return "TestDrive [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", postcode=" + postcode + ", preferredDate=" + preferredDate + ", comments="
				+ comments + ", car=" + car + ", user=" + user + "]";
	}
	
	
	


	
}
