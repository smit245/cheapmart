package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>  {

}
