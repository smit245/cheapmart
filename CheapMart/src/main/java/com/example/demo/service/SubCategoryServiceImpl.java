package com.example.demo.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.SubCategory;
import com.example.demo.repos.SubCategoryRepository;
import com.example.demo.web.dto.SubCategoryFormDto;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public List<SubCategory> getAllSubCategories() {
		return subCategoryRepository.findAll();
	}

	@Override
	public SubCategory save(SubCategoryFormDto subCategoryFormDto) {
		SubCategory subCategory=new SubCategory();
		subCategory.setName(subCategoryFormDto.getName());
		subCategory.setStatus(0);
		subCategory.setCategory(categoryService.getCategoryById(subCategoryFormDto.getCategory()));
		return subCategoryRepository.save(subCategory);
	}

	@Override
	public SubCategory getSubCategoryById(long subCategoryId) {
		Optional<SubCategory> optional=subCategoryRepository.findById(subCategoryId);
		SubCategory subCategory=null;
		if(optional.isPresent()) {
			subCategory=optional.get();
		}
		return subCategory;
	}

	@Override
	public boolean updateSubCategory(SubCategory category) {
		subCategoryRepository.save(category);
		return true;
	}

	@Override
	public Set<SubCategoryFormDto> getSubCategoryByCategoryId(Long id) {
		Set<SubCategoryFormDto> subCategories=new HashSet<SubCategoryFormDto>();
		List<SubCategory> subCategorylst=subCategoryRepository.findSubCategoryByCategory(categoryService.getCategoryById(id));
		Iterator<SubCategory> it=subCategorylst.iterator();
		while(it.hasNext()) {
			SubCategory subCategory=it.next();
			SubCategoryFormDto subCategoryFormDto=new SubCategoryFormDto();
			subCategoryFormDto.setCategory(subCategory.getCategory().getId());
			subCategoryFormDto.setId(subCategory.getId());
			subCategoryFormDto.setName(subCategory.getName());
			subCategoryFormDto.setStatus(subCategory.getStatus());
			subCategories.add(subCategoryFormDto);
		}
		return subCategories;
	}
	
	
}
