package com.example.demo.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		User user=userRepository.findByEmail(registrationDto.getEmail());
		if(user == null) {
			userService.save(registrationDto);
			return "redirect:/?successregister";
		}
		return "redirect:/?alreadyregistered";
	}
}
