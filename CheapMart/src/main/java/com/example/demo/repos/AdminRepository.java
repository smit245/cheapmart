package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Admin;
import com.example.demo.model.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	List<Admin> findByEmail(String email);
}

