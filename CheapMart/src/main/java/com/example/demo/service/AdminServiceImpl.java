package com.example.demo.service;

import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Admin;
import com.example.demo.model.User;
import com.example.demo.repos.AdminRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository userRepo;
	@Override
	public List<User> getAllUserInfo() {
		return userRepo.findAll();
	}
	@Override
	public Optional<User> getUserByID(long id) {
		
		return userRepo.findById(id);
	}
	public void delete(long id) {
        userRepo.deleteById(id);
    }
	@Override
	public User getUserbyId(long id) {
		Optional<User> optional = userRepo.findById(id);
		User user = null;
		if(optional.isPresent())
		{
			user = optional.get();
		}
		else
		{
			throw new RuntimeException("User not found - > "+id);
		}
		return user;
	}
	@Override
	public void saveUser(User user) {
		this.userRepo.save(user);
	}
	@Override
	public Optional<Admin> getAdminByEmail(String email) {
		return null;
	}

}
