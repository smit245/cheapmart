package com.example.demo.service;

import java.util.List;
import com.example.demo.model.SubCategory;
import com.example.demo.web.dto.SubCategoryFormDto;


public interface SubCategoryService {
	List<SubCategory> getAllSubCategories();
	SubCategory getSubCategoryById(long subCategoryId);
	SubCategory save(SubCategoryFormDto subCategoryFormDato);
	boolean updateSubCategory(SubCategory subCategory);
}
