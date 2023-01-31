package com.car.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private int id;
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed")
	private String name;
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	@Size(min = 11, max = 15, message = "min 11 and max 15  characters are allowed")
	private String phone;
	@Column(length = 5000)
	public String messages;
	@ManyToOne()
	private User user;


	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contact(int id,
			@NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 20, message = "min 2 and max 20 characters are allowed") String name,
			@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") String email,
			@Size(min = 11, max = 15, message = "min 11 and max 15  characters are allowed") String phone,
			String messages, User user) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.messages = messages;
		this.user = user;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", messages="
				+ messages + ", user=" + user + "]";
	}

	

}
