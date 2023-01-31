package com.car.dto;

public class CarDTO {
	
	private Long id;
	private int makeId;
	private String model;
	private int yearId;
	private int countriesId;
	private int typeId;
	private int fuelId;
	
	private double price;
	private int mileage;
	private int cc;
	private String color;
	private String regYear;
	private String description;
	private String imgShow;
	private String img1;
	private String img2;
	private String img3;
	private int userId;


	public CarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	public CarDTO(Long id, int makeId, String model, int yearId, int countriesId, int typeId, int fuelId, double price,
			int mileage, int cc, String color, String regYear, String description, String imgShow, String img1,
			String img2, String img3, int userId) {
		super();
		this.id = id;
		this.makeId = makeId;
		this.model = model;
		this.yearId = yearId;
		this.countriesId = countriesId;
		this.typeId = typeId;
		this.fuelId = fuelId;
		this.price = price;
		this.mileage = mileage;
		this.cc = cc;
		this.color = color;
		this.regYear = regYear;
		this.description = description;
		this.imgShow = imgShow;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.userId = userId;
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getCountriesId() {
		return countriesId;
	}
	public void setCountriesId(int countriesId) {
		this.countriesId = countriesId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getFuelId() {
		return fuelId;
	}
	public void setFuelId(int fuelId) {
		this.fuelId = fuelId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRegYear() {
		return regYear;
	}
	public void setRegYear(String regYear) {
		this.regYear = regYear;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgShow() {
		return imgShow;
	}
	public void setImgShow(String imgShow) {
		this.imgShow = imgShow;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}





	@Override
	public String toString() {
		return "CarDTO [id=" + id + ", makeId=" + makeId + ", model=" + model + ", yearId=" + yearId + ", countriesId="
				+ countriesId + ", typeId=" + typeId + ", fuelId=" + fuelId + ", price=" + price + ", mileage="
				+ mileage + ", cc=" + cc + ", color=" + color + ", regYear=" + regYear + ", description=" + description
				+ ", imgShow=" + imgShow + ", img1=" + img1 + ", img2=" + img2 + ", img3=" + img3 + ", userId=" + userId
				+  "]";
	}



	



	
	

	
}
