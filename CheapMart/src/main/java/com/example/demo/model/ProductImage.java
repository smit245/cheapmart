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
@Table(name="productimage")
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumns(foreignKey = @ForeignKey(name="FK_image_product"),value= {
			@JoinColumn(referencedColumnName = "id",name="pid",nullable=false)})
	private Product product;
	
	@Column(nullable = false,length=1000)
	private String path;
	
	@Column(nullable = false,columnDefinition = "integer default 0")
	private Integer status;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPath() {
		return path;
	}

	public Long getId() {
		return id;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
