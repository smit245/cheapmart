package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;
import com.example.demo.web.dto.CategoryFormDto;


public interface CategoryService {
	List<Category> getAllCategories();
	Category getCategoryById(long categoryId);
	Category save(CategoryFormDto categoryFormDato);
	boolean updateCategory(Category category);
}
