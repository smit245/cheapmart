package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {
	List<Product> getAllProductInfo();
	Product getProductbyId(long id);
	void saveProduct(Product product);
}
