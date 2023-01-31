package com.car.dto;

public class CarBiddingDTO {

	private Long id;
	private double bidPrice;
	private Long carId;
	private int userId;


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
		return "CarBidding [id=" + id + ", bidPrice=" + bidPrice + ", carId=" + carId + ", userId=" + userId + "]";
	}
	
	
	
	

}
