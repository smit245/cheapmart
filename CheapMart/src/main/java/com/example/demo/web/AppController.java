package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.web.dto.UserRegistrationDto;


@Controller
public class AppController {
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/about")
	public String viewAboutPage() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String viewContactPage() {
		return "contact";
	}
}