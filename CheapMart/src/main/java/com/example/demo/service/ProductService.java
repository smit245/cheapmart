package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;
import com.example.demo.web.dto.ProductFormDto;

public interface ProductService {
	List<Product> getAllProductInfo();
	Product getProductbyId(long id);
	Product saveProduct(ProductFormDto productFormDto);
}
