package com.car.dto;

public class TestDriveDTO {
	
	Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String postcode;
//	@NotBlank(message = "Model cannot be blank")
//	private String model;
	private String preferredDate;
	public String comments;
	private Long carId;
	private int userId;
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
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "TestDriveDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", postcode=" + postcode + ", preferredDate=" + preferredDate + ", comments="
				+ comments + ", carId=" + carId + ", userId=" + userId + "]";
	}
	
	
	


	
}
