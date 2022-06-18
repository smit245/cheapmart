package com.example.demo.web.dto;

import java.sql.Timestamp;

public class UserRegistrationDto {
	
	private String name;
	private String email;
	private String phone;
	private String pincode;
	private String city;
	private String state;
	private String address;
	private String gender;
	private Timestamp createdAt;
	private String password;
	private int status;
	
	
	
	public UserRegistrationDto() {
	}

	public UserRegistrationDto(String name, String email, String phone, String pincode, String city, String state,
			String address, String gender, String password, int status) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.address = address;
		this.gender = gender;
		this.password = password;
		this.status = status;
		this.createdAt = new Timestamp(System.currentTimeMillis());
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
}
