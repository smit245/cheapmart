package com.example.demo.service;

import com.example.demo.model.ProductImage;

public interface ProductImageService {
	
	ProductImage saveProductImage(ProductImage productImage);
	ProductImage getProductImageById(Long productImageId);
}
