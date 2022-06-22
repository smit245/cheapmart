package com.example.demo.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Admin;
import com.example.demo.repos.AdminRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;
	
	@Override
	public List<Admin> getAdminByEmail(String email) {
		return adminRepo.findByEmail(email);
	}

}
