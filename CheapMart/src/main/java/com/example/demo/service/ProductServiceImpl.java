package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repos.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProductInfo() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductbyId(long id) {
		Optional<Product> optional = productRepository.findById(id);
		Product product = null;
		if(optional.isPresent())
		{
			product = optional.get();
		}
		else
		{
			throw new RuntimeException("Product not found - > "+id);
		}
		return product;
	}

	@Override
	public void saveProduct(Product product) {
		this.productRepository.save(product);
		
	}

	
}
