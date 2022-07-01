package com.example.demo.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="bidding")
public class Bidding {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_bidding_product"),value= {
			@JoinColumn(referencedColumnName = "id",name="pid",nullable=false)})
	private Product product;
	
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_bidding_user"),value= {
			@JoinColumn(referencedColumnName = "id",name="uid",nullable=false)})
	private User user;
	
	@Column(nullable = false)
	private Timestamp createdAt=new Timestamp(System.currentTimeMillis());
	
	@Column(nullable = false,columnDefinition = "integer default 0")
	private int status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
