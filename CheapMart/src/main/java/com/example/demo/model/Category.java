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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="category")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "category",fetch=FetchType.EAGER)
	private Set<SubCategory> subcategory= new HashSet<>();
	
	@OneToMany(mappedBy = "category",fetch=FetchType.LAZY)
	private Set<Product> product= new HashSet<>();
	
	@Column(nullable = false,length=100)
	private String name;
	
	@Column(nullable = false,length=100)
	private String image;
	
	@Column(nullable = false,columnDefinition = "integer default 0")
	private Integer status;
	
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

}