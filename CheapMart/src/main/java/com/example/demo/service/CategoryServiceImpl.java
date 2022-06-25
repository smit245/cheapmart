package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repos.CategoryRepository;
import com.example.demo.web.dto.CategoryFormDto;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(CategoryFormDto categoryFormDto) {
		Category category=new Category();
		category.setName(categoryFormDto.getName());
		category.setImage(categoryFormDto.getImage());
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(long categoryId) {
		Optional<Category> optional=categoryRepository.findById(categoryId);
		Category category=null;
		if(optional.isPresent()) {
			category=optional.get();
		}
		return category;
	}

	@Override
	public boolean updateCategory(Category category) {
		categoryRepository.save(category);
		return true;
	}
	
	
}
