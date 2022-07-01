package com.example.demo.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.web.dto.ProductFormDto;
import com.example.demo.web.dto.UserRegistrationDto;


@Controller
public class AppController {
	
	@Autowired
	CategoryService categoryService;
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@ModelAttribute("categories")
	public List<Category> categories(){
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
//		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
//		UserSession userSession=(UserSession)auth.getPrincipal();
//		System.out.println("Id:"+userSession.getId());
//		System.out.println("Name:"+userSession.getName());
//		System.out.println("Pincode:"+userSession.getPincode());
		model.addAttribute("title", "Home");
		return "index";
	}
	
	@GetMapping("/about")
	public String viewAboutPage(Model model) {
		model.addAttribute("title", "About Us");
		return "about";
	}
	
	@GetMapping("/contact")
	public String viewContactPage(Model model) {
		model.addAttribute("title", "Contact Us");
		return "contact";
	}
	
	@GetMapping("/addproducts")
	public String viewAddProductPage(@ModelAttribute("productFormDto") ProductFormDto productFormDto,Model model) {
		
		model.addAttribute("title", "Add Product");
		return "addproduct";
	}
}