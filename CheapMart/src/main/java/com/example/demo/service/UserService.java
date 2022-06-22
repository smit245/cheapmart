package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.model.User;
import com.example.demo.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	
	List<User> getAllUserInfo();
	Optional<User> getUserByID(long id);
	void delete(long id);
	User getUserbyId(long id);
	void saveUser(User user);
}
