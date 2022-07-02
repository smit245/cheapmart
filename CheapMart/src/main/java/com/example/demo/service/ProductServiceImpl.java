package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repos.ProductRepository;
import com.example.demo.web.dto.ProductFormDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService userService;
	
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
	public Product saveProduct(ProductFormDto productFormDto) {
		Product product=new Product();
		product.setName(productFormDto.getName());
		product.setPrice(productFormDto.getPrice());
		product.setCategory(categoryService.getCategoryById(productFormDto.getCategory()));
		product.setSubcategory(subCategoryService.getSubCategoryById(productFormDto.getSubcategory()));
		product.setDescription(productFormDto.getDescription());
		product.setAddress(productFormDto.getAddress());
		product.setEmail(productFormDto.getEmail());
		product.setCity(productFormDto.getCity());
		product.setPincode(productFormDto.getPincode());
		product.setState(productFormDto.getState());
		product.setStartTime(productFormDto.getStartTime());
		product.setEndTime(productFormDto.getEndTime());
		product.setEntryFees(productFormDto.getEntryFees());
		product.setIncrementAmt(productFormDto.getIncrementAmt());
		product.setIsBidding(productFormDto.getIsBidding());
		product.setMinIncrementAmt(productFormDto.getMinIncrementAmt());
		product.setUser(userService.getUserbyId(productFormDto.getUser()));
		product.setStatus(0);
		return productRepository.save(product);
		
	}

	
}
