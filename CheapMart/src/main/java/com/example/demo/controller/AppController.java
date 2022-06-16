package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.User;

@Controller
public class AppController {
	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public  String viewRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@GetMapping("/admin")
	public String viewAdminIndex() {
		return "admin/index";
	}
}
