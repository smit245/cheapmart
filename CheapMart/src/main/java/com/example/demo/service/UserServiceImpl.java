package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepository;
import com.example.demo.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user=new User();
		user.setName(registrationDto.getName());
		user.setAddress(registrationDto.getAddress());
		user.setCity(registrationDto.getCity());
		user.setPincode(registrationDto.getPincode());
		user.setState(registrationDto.getState());
		user.setEmail(registrationDto.getEmail());
		user.setPhone(registrationDto.getPhone());
		user.setGender(registrationDto.getGender());
		user.setPassword(registrationDto.getPassword());
		user.setStatus(registrationDto.getStatus());
		return userRepository.save(user);
	}
	

}
