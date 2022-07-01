package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Table(name="subcategory")
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cid",nullable=false,referencedColumnName = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Category category;
	
	@OneToMany(mappedBy = "subcategory",fetch=FetchType.LAZY)
	private Set<Product> product= new HashSet<>();
	
	@Column(nullable = false,length=100)
	private String name;
	
	
	@Column(nullable = false,columnDefinition = "integer default 0")
	private int status;
	
	public Long getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}