package com.example.demo.web.dto;

import java.sql.Timestamp;

public class ProductFormDto {

	private Long id;
	private Long category;
	
	private Long subcategory;
	private Long user;
	private String name;
	private String description;
	private double price;
	private String email;
	private String pincode;
	private String city;
	private String state;
	private String address;
	private int isBidding;
	private double minIncrementAmt;
	private double entryFees;
	private double incrementAmt;
	private double bidPrice;
	private Timestamp startTime;
	private Timestamp endTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Long subcategory) {
		this.subcategory = subcategory;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getIsBidding() {
		return isBidding;
	}
	public void setIsBidding(int isBidding) {
		this.isBidding = isBidding;
	}
	public double getMinIncrementAmt() {
		return minIncrementAmt;
	}
	public void setMinIncrementAmt(double minIncrementAmt) {
		this.minIncrementAmt = minIncrementAmt;
	}
	public double getEntryFees() {
		return entryFees;
	}
	public void setEntryFees(double entryFees) {
		this.entryFees = entryFees;
	}
	public double getIncrementAmt() {
		return incrementAmt;
	}
	public void setIncrementAmt(double incrementAmt) {
		this.incrementAmt = incrementAmt;
	}
	public double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
}
