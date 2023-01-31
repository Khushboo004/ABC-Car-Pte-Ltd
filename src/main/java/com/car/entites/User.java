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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed")
	private String name;
	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	@Size(min = 8, message = "min 8  characters are allowed")
	private String password;
	@Size(min = 11, max = 15, message = "min 11 and max 15  characters are allowed")
	private String phone;
	private boolean enable;
	private String imageUrl;
	@Column(length = 5000)
	private String about;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private List<Car> cars = new ArrayList<Car>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private List<CarBidding> carBiddings = new ArrayList<CarBidding>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private List<TestDrive> testDrives = new ArrayList<TestDrive>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();

	public User() {
		super();
	}

	public User(Long id,
			@NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed") String name,
			@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") String email,
			@Size(min = 8, message = "min 8  characters are allowed") String password,
			@Size(min = 11, max = 15, message = "min 11 and max 15  characters are allowed") String phone,
			boolean enable, String imageUrl, String about, List<Car> cars, List<CarBidding> carBiddings,
			List<TestDrive> testDrives, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.enable = enable;
		this.imageUrl = imageUrl;
		this.about = about;
		this.cars = cars;
		this.carBiddings = carBiddings;
		this.testDrives = testDrives;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<CarBidding> getCarBiddings() {
		return carBiddings;
	}

	public List<TestDrive> getTestDrives() {
		return testDrives;
	}

	public void setTestDrives(List<TestDrive> testDrives) {
		this.testDrives = testDrives;
	}

	public void setCarBiddings(List<CarBidding> carBiddings) {
		this.carBiddings = carBiddings;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", enable=" + enable + ", imageUrl=" + imageUrl + ", about=" + about + ", cars=" + cars
				+ ", carBiddings=" + carBiddings + ", testDrives=" + testDrives + ", roles=" + roles + "]";
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = roles.iterator();
		while (iterator.hasNext()) {
			Role role = iterator.next();
			if (role.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

}
