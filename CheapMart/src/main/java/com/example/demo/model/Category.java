package com.example.demo.model;

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
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "category",fetch=FetchType.EAGER)
	private Set<SubCategory> subcategory= new HashSet<>();
	
	@Column(nullable = false,length=100)
	private String name;
	
	@Column(nullable = false,length=100)
	private String image;
	
	@Column(nullable = false,columnDefinition = "integer default 0")
	private int status;
	
	public Set<SubCategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Set<SubCategory> subcategory) {
		this.subcategory = subcategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	
}