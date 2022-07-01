package com.example.demo.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_product_subcategory"),value= {
			@JoinColumn(referencedColumnName = "id",name = "scid",nullable=false)})
	private SubCategory subcategory;
	
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_product_user"),value= {
			@JoinColumn(referencedColumnName = "id",name = "uid",nullable=false)})
	private User user;
	
	@OneToMany(mappedBy = "product",fetch=FetchType.EAGER)
	private Set<ProductImage> productimage = new HashSet<>();
	
	@OneToMany(mappedBy = "product",fetch=FetchType.EAGER)
	private Set<Order> order=new HashSet<>();
	
	


	@Column(nullable = false,length=100)
	private String name;
	
	@Column(nullable = false,length=2000)
	private String description;
	
	@Column(nullable= false)
	private double price;
	
	@Column(nullable = false)
	private String pincode;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	
	//Bidding Details
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_bidder_product"),value= {
			@JoinColumn(referencedColumnName = "id",name="bidderid",nullable=false)})
	private User bidder;
	
	@OneToMany(mappedBy = "product",fetch=FetchType.EAGER)
	private Set<Bidding> bidding=new HashSet<>();
	
	@Column(nullable=false)
	private int isBidding;
	
	

	@Column(nullable=true)
	private double minIncrementAmt;
	
	@Column(nullable=true)
	private double entryFees;
	
	@Column(nullable=true)
	private double incrementAmt;
	
	@Column(nullable=true)
	private double bidPrice;	
	
	@Column(nullable=true)
	private Timestamp startTime;
	
	@Column(nullable=true)
	private Timestamp endTime;
	
	@Column(nullable = false)
	private Timestamp createdAt=new Timestamp(System.currentTimeMillis());
	
	@Column(nullable = false,columnDefinition = "integer default 0")
	private int status;

	
	public Timestamp getCreatedAt() {
		return createdAt;
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

	public User getBidder() {
		return bidder;
	}

	public void setBidder(User bidder) {
		this.bidder = bidder;
	}

	public Set<Bidding> getBidding() {
		return bidding;
	}

	public void setBidding(Set<Bidding> bidding) {
		this.bidding = bidding;
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

	public void setId(Long id) {
		this.id = id;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}


	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<ProductImage> getProductimage() {
		return productimage;
	}


	public void setProductimage(Set<ProductImage> productimage) {
		this.productimage = productimage;
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


	public Long getId() {
		return id;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}
	
	public int getIsBidding() {
		return isBidding;
	}

	public void setIsBidding(int isBidding) {
		this.isBidding = isBidding;
	}
}