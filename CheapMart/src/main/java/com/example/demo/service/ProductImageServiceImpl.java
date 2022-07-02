package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductImage;
import com.example.demo.repos.ProductImageRepository;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	
	@Autowired
	ProductImageRepository productImageRepository;
	
	@Override
	public ProductImage saveProductImage(ProductImage productImage) {
		productImage.setStatus(0);
		return productImageRepository.save(productImage);
	}

	@Override
	public ProductImage getProductImageById(Long productImageId) {
		ProductImage productImage=null;
		Optional<ProductImage> pI=productImageRepository.findById(productImageId);
		if(pI.isPresent()) {
			productImage=pI.get();
		}
		return productImage;
	}

}
