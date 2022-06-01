package com.example.demo.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
	private Set<Product> product = new HashSet<>();
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<Bidding> bidding = new HashSet<>();
	
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	public Set<Bidding> getBidding() {
		return bidding;
	}
	public void setBidding(Set<Bidding> bidding) {
		this.bidding = bidding;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable = false,length=100)
	private String name;

	@Column(nullable =  false,unique=true,length = 45)
	private String email;
	
	@Column(nullable = false,length=10)
	private String phone;

	@Column(nullable = false,length=50)
	private String City;

	@Column(nullable = false,length=10)
	private String Gender;

	@Column(nullable = false)
	private Timestamp createdAt=new Timestamp(System.currentTimeMillis());

	@Column(nullable = false,length=64)
	private String password;
	
	@Column(nullable  = true,columnDefinition = "integer default 0")
	private int status;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}