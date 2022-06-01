package com.example.demo.model;

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
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_order_product"),value= {
			@JoinColumn(referencedColumnName = "id",name="pid",nullable=false)})
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="uid",nullable=false)
	private User user;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private String pincode;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = true)
	private String paymentId;
	
	@Column(nullable = false)
	private String paymentMode;
	
	@Column(nullable  = false,columnDefinition = "integer default 0")
	private int status;
}