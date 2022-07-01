package com.example.demo.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
	@JoinColumns(foreignKey = @ForeignKey(name="FK_product_category"),value= {
			@JoinColumn(referencedColumnName = "id",name = "cid",nullable=false)})
	private Category category;

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
	private Double price;
	
	@Column(nullable=false, length=100)
	private String email;
	
	@Column(nullable = false,  length=6)
	private String pincode;
	
	@Column(nullable = false,length=50)
	private String city;
	
	@Column(nullable = false,length=50)
	private String state;
	
	@Column(nullable=false,length=500)
	private String address;
	
	
	//Bidding Details
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_bidder_product"),value= {
			@JoinColumn(referencedColumnName = "id",name="bidderid",nullable=true)})
	private User bidder;
	
	@OneToMany(mappedBy = "product",fetch=FetchType.EAGER)
	private Set<Bidding> bidding=new HashSet<>();
	
	@Column(nullable=false)
	private Integer isBidding;
	
	@Column(nullable=true)
	private Double minIncrementAmt;
	
	@Column(nullable=true)
	private Double entryFees;
	
	@Column(nullable=true)
	private Double incrementAmt;
	
	@Column(nullable=true)
	private Double bidPrice;	
	
	@Column(nullable=true)
	private LocalDateTime startTime;
	
	@Column(nullable=true)
	private LocalDateTime endTime;
	
	@Column(nullable = false)
	private Timestamp createdAt=new Timestamp(System.currentTimeMillis());
	
	@Column(nullable = false,columnDefinition = "integer default 0")
	private Integer status=0;

	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}
	
	public Integer getIsBidding() {
		return isBidding;
	}

	public void setIsBidding(Integer isBidding) {
		this.isBidding = isBidding;
	}
}