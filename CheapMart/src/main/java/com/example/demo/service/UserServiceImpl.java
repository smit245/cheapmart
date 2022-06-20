package com.example.demo.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepository;
import com.example.demo.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
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
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setStatus(registrationDto.getStatus());
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid Username Password");
		}
		Collection<String> c=new HashSet<String>();
		c.add("USER");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), myRolesToAuthorities(c));
	}
	
	private Collection<? extends GrantedAuthority> myRolesToAuthorities(Collection<String> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority("USER")).collect(Collectors.toList());
	}
	
	

}
