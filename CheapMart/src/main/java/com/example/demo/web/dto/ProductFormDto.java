package com.example.demo.web.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ProductFormDto {

	private Long id;
	private Long user;
	private Long category;
	private Long subcategory;
	private String name;
	private String description;
	private Double price;
	private String email;
	private String pincode;
	private String city;
	private String state;
	private String address;
	private Integer isBidding;
	private Double minIncrementAmt=0.0;
	private Double entryFees;
	private Double incrementAmt;
	private Double bidPrice;
	private Integer status=0;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public Long getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Long subcategory) {
		this.subcategory = subcategory;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
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
	public Integer getIsBidding() {
		return isBidding;
	}
	public void setIsBidding(Integer isBidding) {
		this.isBidding = isBidding;
	}
	public Double getMinIncrementAmt() {
		return minIncrementAmt;
	}
	public void setMinIncrementAmt(Double minIncrementAmt) {
		this.minIncrementAmt = minIncrementAmt;
	}
	public Double getEntryFees() {
		return entryFees;
	}
	public void setEntryFees(Double entryFees) {
		this.entryFees = entryFees;
	}
	public Double getIncrementAmt() {
		return incrementAmt;
	}
	public void setIncrementAmt(Double incrementAmt) {
		this.incrementAmt = incrementAmt;
	}
	public Double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
}
