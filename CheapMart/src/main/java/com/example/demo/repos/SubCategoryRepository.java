package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;
import com.example.demo.model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	

	List<SubCategory> findSubCategoryByCategory(Category category);
}
